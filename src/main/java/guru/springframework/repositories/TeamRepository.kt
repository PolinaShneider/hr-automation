package guru.springframework.repositories

import guru.springframework.domain.model.Team
import org.springframework.data.repository.CrudRepository

interface TeamRepository : CrudRepository<Team, Int>
