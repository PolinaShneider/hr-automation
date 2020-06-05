package guru.springframework.repositories

import guru.springframework.domain.entities.Interviewer
import org.springframework.data.repository.CrudRepository

interface InterviewersRepository : CrudRepository<Interviewer, Int>