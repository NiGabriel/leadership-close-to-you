package own.leadership.leadershipctyou.dto

data class ReportStatsDto(
    val total: Int,
    val resolved: Int,
    val rate: String,      // e.g., "92%"
    val avgTime: String    // e.g., "4h"
)
