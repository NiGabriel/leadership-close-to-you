package own.leadership.leadershipctyou.repository

import org.springframework.data.jpa.repository.JpaRepository
import own.leadership.leadershipctyou.model.Leader
import java.util.*

interface LeaderRepository : JpaRepository<Leader, UUID>
