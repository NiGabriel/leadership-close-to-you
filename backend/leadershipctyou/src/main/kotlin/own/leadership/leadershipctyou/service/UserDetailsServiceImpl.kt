package own.leadership.leadershipctyou.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import own.leadership.leadershipctyou.repository.UserRepository

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(phoneNumber: String): UserDetails {
        val user = userRepository.findByPhoneNumber(phoneNumber)
            ?: throw UsernameNotFoundException("User with phone $phoneNumber not found")

        return org.springframework.security.core.userdetails.User.builder()
            .username(user.phoneNumber)
            .password(user.password)
            .roles(user.role.name) // CITIZEN, LEADER, ADMIN
            .build()
    }
}