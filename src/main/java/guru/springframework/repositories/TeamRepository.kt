package guru.springframework.repositories

import guru.springframework.domain.entities.Team
import org.springframework.data.repository.CrudRepository

interface TeamRepository : CrudRepository<Team, Int>
