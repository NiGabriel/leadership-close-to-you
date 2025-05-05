package own.leadership.leadershipctyou.controller

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import own.leadership.leadershipctyou.dto.*
import own.leadership.leadershipctyou.model.*
import own.leadership.leadershipctyou.repository.*
import own.leadership.leadershipctyou.utils.JwtUtil

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = ["*"])
class AuthController(
    private val userRepository: UserRepository,
    private val locationRepository: LocationRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil
) {

    @PostMapping("/signup")
    fun signup(@RequestBody req: SignupRequest): User {
        val location = locationRepository.findById(req.locationId).orElseThrow()
        val encodedPw = passwordEncoder.encode(req.password)

        val user = User(
            name = req.name,
            phoneNumber = req.phoneNumber,
            email = req.email,
            role = Role.valueOf(req.role),
            location = location,
            password = encodedPw
        )

        return userRepository.save(user)
    }


    @PostMapping("/login")
    fun login(@RequestBody req: LoginRequest): AuthResponse {
        val auth = UsernamePasswordAuthenticationToken(req.phoneNumber, req.password)
        authenticationManager.authenticate(auth)

        val user = userRepository.findByPhoneNumber(req.phoneNumber)!!
        val token = jwtUtil.generateToken(user.phoneNumber, user.role.name)

        return AuthResponse(token, user.name, user.role.name)
    }
}
