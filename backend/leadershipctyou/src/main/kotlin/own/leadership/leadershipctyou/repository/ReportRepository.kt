package own.leadership.leadershipctyou.repository

import org.springframework.data.jpa.repository.JpaRepository
import own.leadership.leadershipctyou.model.Report
import java.util.*

interface ReportRepository : JpaRepository<Report, UUID>