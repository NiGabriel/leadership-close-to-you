package own.leadership.leadershipctyou.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "leaders")
data class Leader(
    @Id @GeneratedValue
    val id: UUID? = null,

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    val user: User,

    val positionTitle: String,
    val officePhone: String,
    val available: Boolean = true
)