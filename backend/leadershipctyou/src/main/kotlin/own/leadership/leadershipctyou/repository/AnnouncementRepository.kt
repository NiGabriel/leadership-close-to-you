package own.leadership.leadershipctyou.repository

import org.springframework.data.jpa.repository.JpaRepository
import own.leadership.leadershipctyou.model.Announcement
import java.util.*

interface AnnouncementRepository : JpaRepository<Announcement, UUID>{
    fun findTop5ByOrderByCreatedAtDesc(): List<Announcement>

}