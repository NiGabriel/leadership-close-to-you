package own.leadership.leadershipctyou

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LeadershipctyouApplication

fun main(args: Array<String>) {
    val dotenv = Dotenv.configure()
        .filename(".env")
        .ignoreIfMalformed()
        .ignoreIfMissing()
        .load()

    dotenv.entries().forEach { entry ->
        System.setProperty(entry.key, entry.value)
    }
    runApplication<LeadershipctyouApplication>(*args)
}
