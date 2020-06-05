package guru.springframework.repositories

import guru.springframework.domain.entities.Candidate
import org.springframework.data.repository.CrudRepository

interface CandidateRepository : CrudRepository<Candidate, Int>
