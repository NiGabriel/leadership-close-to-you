package own.leadership.leadershipctyou.repository

import org.springframework.data.jpa.repository.JpaRepository
import own.leadership.leadershipctyou.model.Location
import java.util.*

interface LocationRepository : JpaRepository<Location, UUID>