package own.leadership.leadershipctyou.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import own.leadership.leadershipctyou.model.Leader
import java.util.*

interface LeaderRepository : JpaRepository<Leader, UUID>{
    @Query("SELECT l FROM Leader l WHERE l.user.location.id = :locationId")
    fun findByLocationId(@Param("locationId") locationId: UUID): List<Leader>



}
