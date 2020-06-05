package guru.springframework.services.position

import guru.springframework.domain.entities.Position
import guru.springframework.domain.entities.Status


interface PositionService {
    fun listAllPositions(): Iterable<Position>

    fun listOpenPositions(): Iterable<Position>

    fun getPositionById(id: Int?): Position

    fun savePosition(position: Position): Position
}
