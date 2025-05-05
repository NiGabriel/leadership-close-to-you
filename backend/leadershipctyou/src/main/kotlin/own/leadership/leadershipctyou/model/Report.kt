package own.leadership.leadershipctyou.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "reports")
data class Report(
    @Id @GeneratedValue
    val id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    val user: User? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    val location: Location? = null,

    val content: String = "",

    @Enumerated(EnumType.STRING)
    var status: ReportStatus = ReportStatus.SUBMITTED,

    val createdAt: Date = Date(),
    var resolvedAt: Date? = null,

    @Column(name = "response_time_hours")
    var responseTimeHours: Double? = null

)

enum class ReportStatus {
    SUBMITTED, IN_PROGRESS, RESOLVED
}