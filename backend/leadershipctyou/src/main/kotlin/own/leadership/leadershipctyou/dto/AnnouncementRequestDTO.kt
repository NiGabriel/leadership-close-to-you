package own.leadership.leadershipctyou.dto

import own.leadership.leadershipctyou.model.AnnouncementChannel
import java.util.*

data class AnnouncementRequestDTO(
    val leaderId: UUID,
    val locationId: UUID,
    val title: String,
    val message: String,
    val channel: AnnouncementChannel = AnnouncementChannel.APP
)