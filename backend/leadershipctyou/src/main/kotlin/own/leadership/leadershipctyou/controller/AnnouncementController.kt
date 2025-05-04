package own.leadership.leadershipctyou.controller

import org.springframework.web.bind.annotation.*
import own.leadership.leadershipctyou.dto.AnnouncementRequestDTO
import own.leadership.leadershipctyou.model.Announcement
import own.leadership.leadershipctyou.model.AnnouncementChannel
import own.leadership.leadershipctyou.repository.AnnouncementRepository
import own.leadership.leadershipctyou.repository.LeaderRepository
import own.leadership.leadershipctyou.repository.LocationRepository
import own.leadership.leadershipctyou.repository.UserRepository
import own.leadership.leadershipctyou.service.FirebaseService
import java.util.*
import own.leadership.leadershipctyou.service.TwilioService

@RestController
@RequestMapping("/api/announcements")
class AnnouncementController(
    private val announcementRepository: AnnouncementRepository,
    private val leaderRepository: LeaderRepository,
    private val locationRepository: LocationRepository,
    private val userRepository: UserRepository,
    private val twilioService: TwilioService,
    private val firebaseService: FirebaseService
) {

    @GetMapping
    fun getAll(): List<Announcement> = announcementRepository.findAll()

    @PostMapping
    fun create(@RequestBody dto: AnnouncementRequestDTO): Announcement {
        val leader = leaderRepository.findById(dto.leaderId).orElseThrow()
        val location = locationRepository.findById(dto.locationId).orElseThrow()

        val announcement = Announcement(
            leader = leader,
            location = location,
            title = dto.title,
            message = dto.message,
            channel = dto.channel
        )

        val saved = announcementRepository.save(announcement)

        // This part is required to trigger SMS
        if (dto.channel == AnnouncementChannel.PUSH || dto.channel == AnnouncementChannel.ALL) {
            val recipients = userRepository.findAll().filter {
                it.location.id == location.id && !it.fcmToken.isNullOrBlank()
            }

            recipients.forEach { user ->
                firebaseService.sendPushNotification(
                    fcmToken = user.fcmToken!!,
                    title = "[${dto.title}]",
                    message = dto.message
                )
            }

            println("✅ Sent PUSH to ${recipients.size} users")
        }


        return saved
    }

}