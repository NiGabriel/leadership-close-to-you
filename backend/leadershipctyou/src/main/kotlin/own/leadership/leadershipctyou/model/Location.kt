package own.leadership.leadershipctyou.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "locations")
data class Location(
    @Id @GeneratedValue
    val id: UUID? = null,

    val province: String,
    val district: String,
    val sector: String,
    val cell: String,
    val village: String? = null
)