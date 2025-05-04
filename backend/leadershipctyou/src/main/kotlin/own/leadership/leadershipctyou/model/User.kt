package own.leadership.leadershipctyou.model

import jakarta.persistence.*
import java.util.*
import com.fasterxml.jackson.annotation.JsonIgnore


@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue
    val id: UUID? = null,

    val name: String,

    @Column(unique = true)
    val phoneNumber: String,

    val email: String? = null,

    @Column(nullable = false)
    val password: String,

    @Enumerated(EnumType.STRING)
    val role: Role = Role.CITIZEN,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @JsonIgnore
    val location: Location,


    val registeredAt: Date = Date(),

    @Column(name = "fcm_token")
    var fcmToken: String? = null
)

enum class Role {
    CITIZEN, LEADER, ADMIN
}