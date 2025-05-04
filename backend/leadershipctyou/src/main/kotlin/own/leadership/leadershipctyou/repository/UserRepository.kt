package own.leadership.leadershipctyou.repository

import own.leadership.leadershipctyou.model.*
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {
    fun findByPhoneNumber(phoneNumber: String): User?
}