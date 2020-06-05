package guru.springframework.repositories

import guru.springframework.domain.entities.Position
import org.springframework.data.repository.CrudRepository

interface PositionRepository : CrudRepository<Position, Int>
