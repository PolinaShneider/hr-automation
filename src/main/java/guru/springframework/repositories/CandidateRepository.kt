package guru.springframework.repositories

import guru.springframework.domain.model.Candidate
import org.springframework.data.repository.CrudRepository

interface CandidateRepository : CrudRepository<Candidate, Int>
