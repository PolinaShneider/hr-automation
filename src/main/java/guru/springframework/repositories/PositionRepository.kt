package guru.springframework.repositories

import guru.springframework.domain.model.Position
import org.springframework.data.repository.CrudRepository

interface PositionRepository : CrudRepository<Position, Int>
