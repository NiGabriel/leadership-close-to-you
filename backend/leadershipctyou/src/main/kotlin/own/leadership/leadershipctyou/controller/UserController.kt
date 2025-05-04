package own.leadership.leadershipctyou.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import own.leadership.leadershipctyou.dto.DeviceTokenDTO
import own.leadership.leadershipctyou.model.User
import own.leadership.leadershipctyou.repository.UserRepository

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userRepository: UserRepository
) {

    @GetMapping
    fun getAll(): List<User> = userRepository.findAll()

    @PostMapping
    fun createUser(@RequestBody user: User): User = userRepository.save(user)


    @PostMapping("/device-token")
    fun saveDeviceToken(
        @AuthenticationPrincipal userDetails: UserDetails,
        @RequestBody body: DeviceTokenDTO
    ): String {
        val user = userRepository.findByPhoneNumber(userDetails.username) ?: return "User not found"
        user.fcmToken = body.token
        userRepository.save(user)
        return "Token saved"
    }
}