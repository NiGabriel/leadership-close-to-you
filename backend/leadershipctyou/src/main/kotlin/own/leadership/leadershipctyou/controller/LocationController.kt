package own.leadership.leadershipctyou.controller

import org.springframework.web.bind.annotation.*
import own.leadership.leadershipctyou.model.Location
import own.leadership.leadershipctyou.repository.LocationRepository

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = ["*"])
class LocationController(
    private val locationRepository: LocationRepository
) {

    @GetMapping
    fun getAll(): List<Location> = locationRepository.findAll()

    @PostMapping
    fun create(@RequestBody location: Location): Location =
        locationRepository.save(location)
}