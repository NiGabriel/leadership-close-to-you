package own.leadership.leadershipctyou.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import own.leadership.leadershipctyou.dto.ReportRequestDTO
import own.leadership.leadershipctyou.dto.ReportStatsDto
import own.leadership.leadershipctyou.model.Report
import own.leadership.leadershipctyou.model.ReportStatus
import own.leadership.leadershipctyou.repository.LocationRepository
import own.leadership.leadershipctyou.repository.ReportRepository
import own.leadership.leadershipctyou.repository.UserRepository
import java.time.ZoneId
import java.util.*
import java.time.Duration

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = ["*"])
class ReportController(
    private val reportRepository: ReportRepository,
    private val userRepository: UserRepository,
    private val locationRepository: LocationRepository
) {

    @PostMapping
    fun submit(@RequestBody req: ReportRequestDTO): Report {
        val user = userRepository.findById(req.userId).orElseThrow()
        val location = locationRepository.findById(req.locationId).orElseThrow()

        val report = Report(
            user = user,
            location = location,
            content = req.content
        )

        return reportRepository.save(report)
    }

    @GetMapping
    fun getAll(): List<Report> = reportRepository.findAll()

    @PutMapping("/{id}/resolve")
    fun resolve(@PathVariable id: UUID): Report {
        val report = reportRepository.findById(id).orElseThrow()
        report.status = ReportStatus.RESOLVED
        report.resolvedAt = Date()

        val duration = Duration.between(
            report.createdAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
            report.resolvedAt!!.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        )

        report.responseTimeHours = duration.toMinutes().toDouble() / 60.0

        return reportRepository.save(report)
    }


    @GetMapping("/stats")
    fun getReportStats(@AuthenticationPrincipal principal: org.springframework.security.core.userdetails.User): ReportStatsDto {
        val user = userRepository.findByPhoneNumber(principal.username) ?: throw Exception("User not found")

        val reports = reportRepository.findByUserId(user.id!!)
        val total = reports.size
        val resolvedReports = reports.filter { it.status == ReportStatus.RESOLVED && it.resolvedAt != null }

        val resolved = resolvedReports.size
        val rate = if (total > 0) "${(resolved * 100 / total)}%" else "0%"

        val responseTimes = resolvedReports.mapNotNull {
            val created = it.createdAt?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDateTime()
            val resolved = it.resolvedAt?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDateTime()
            if (created != null && resolved != null) {
                Duration.between(created, resolved).toHours().toDouble()
            } else null
        }.map { it.toDouble() }

        val avgHours = if (responseTimes.isNotEmpty()) responseTimes.average() else 0.0

        return ReportStatsDto(total, resolved, rate, "${avgHours.toInt()}h")
    }


}