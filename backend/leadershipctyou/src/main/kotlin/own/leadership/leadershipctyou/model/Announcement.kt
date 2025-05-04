package own.leadership.leadershipctyou.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "announcements")
data class Announcement(
    @Id @GeneratedValue
    val id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id")
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    val leader: Leader? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    val location: Location? = null,

    val title: String = "",
    val message: String = "",

    @Enumerated(EnumType.STRING)
    val channel: AnnouncementChannel = AnnouncementChannel.APP,

    val createdAt: Date = Date()
)

enum class AnnouncementChannel {
    APP, SMS, PUSH, ALL
}