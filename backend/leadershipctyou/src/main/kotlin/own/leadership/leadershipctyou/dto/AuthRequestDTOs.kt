package own.leadership.leadershipctyou.dto

import java.util.*

data class SignupRequest(
    val name: String,
    val phoneNumber: String,
    val email: String?,
    val password: String,
    val role: String = "CITIZEN",
    val locationId: UUID
)

data class LoginRequest(
    val phoneNumber: String,
    val password: String
)

data class AuthResponse(
    val token: String,
    val role: String
)