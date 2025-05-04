package own.leadership.leadershipctyou.controller

import org.springframework.web.bind.annotation.*
import own.leadership.leadershipctyou.dto.ReportRequestDTO
import own.leadership.leadershipctyou.model.Report
import own.leadership.leadershipctyou.model.ReportStatus
import own.leadership.leadershipctyou.repository.LocationRepository
import own.leadership.leadershipctyou.repository.ReportRepository
import own.leadership.leadershipctyou.repository.UserRepository
import java.util.*

@RestController
@RequestMapping("/api/reports")
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
        return reportRepository.save(report)
    }
}