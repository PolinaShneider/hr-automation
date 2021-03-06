package guru.springframework.builder

import guru.springframework.domain.entities.Position
import org.springframework.stereotype.Component

@Component
class PositionBuilder {
    fun create(
        teamId: Int,
        requirements: String,
        title: String,
        isOpened: Boolean = true
    ): Position {
        val position = Position()
        position.title = title
        position.requirements = requirements
        position.teamId = teamId
        position.isOpened = isOpened
        return position
    }
}