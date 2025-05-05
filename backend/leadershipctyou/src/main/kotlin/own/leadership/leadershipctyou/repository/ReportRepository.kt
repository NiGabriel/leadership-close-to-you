package own.leadership.leadershipctyou.repository

import org.springframework.data.jpa.repository.JpaRepository
import own.leadership.leadershipctyou.model.Report
import own.leadership.leadershipctyou.model.User
import java.util.*

interface ReportRepository : JpaRepository<Report, UUID>{
    fun findAllByUser(user: User): List<Report>
    fun findByUserId(userId: UUID): List<Report>


}