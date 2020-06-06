package guru.springframework.builder

import guru.springframework.domain.entities.*
import org.springframework.stereotype.Component

@Component
class TeamBuilder {
    fun create(
        name: String
    ): Team {
        return Team(name)
    }
}