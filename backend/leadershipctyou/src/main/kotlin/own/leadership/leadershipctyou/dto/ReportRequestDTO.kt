package own.leadership.leadershipctyou.dto

import java.util.*

data class ReportRequestDTO(
    val userId: UUID,
    val locationId: UUID,
    val content: String
)