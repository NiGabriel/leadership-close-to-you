package own.leadership.leadershipctyou.controller

import org.springframework.web.bind.annotation.*
import own.leadership.leadershipctyou.model.Leader
import own.leadership.leadershipctyou.repository.LeaderRepository

@RestController
@RequestMapping("/api/leaders")
class LeaderController(
    private val leaderRepository: LeaderRepository
) {

    @GetMapping
    fun getAll(): List<Leader> = leaderRepository.findAll()
}