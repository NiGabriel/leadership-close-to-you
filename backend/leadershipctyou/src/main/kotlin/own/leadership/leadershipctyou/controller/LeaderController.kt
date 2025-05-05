package own.leadership.leadershipctyou.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import own.leadership.leadershipctyou.model.Leader
import own.leadership.leadershipctyou.model.User
import own.leadership.leadershipctyou.repository.LeaderRepository
import own.leadership.leadershipctyou.repository.UserRepository
import java.util.*

@RestController
@RequestMapping("/api/leaders")
@CrossOrigin(origins = ["*"])
class LeaderController(
    private val leaderRepository: LeaderRepository,
    private val userRepository: UserRepository
) {

    @GetMapping
    fun getAll(): List<Leader> = leaderRepository.findAll()

    @GetMapping("/nearby")
    fun getNearbyLeaders(@AuthenticationPrincipal userPrincipal: org.springframework.security.core.userdetails.User): List<Leader> {
        val user = userRepository.findByPhoneNumber(userPrincipal.username)
            ?: throw Exception("User not found")

        val locationId = user.location.id ?: throw Exception("User has no location assigned")

        println("Logged in user: ${user.phoneNumber}, Role: ${user.role}, Location: ${user.location.id}")


        return leaderRepository.findByLocationId(locationId)
    }
}