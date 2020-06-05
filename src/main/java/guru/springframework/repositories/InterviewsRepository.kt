package guru.springframework.repositories

import guru.springframework.domain.entities.Interview
import org.springframework.data.repository.CrudRepository

interface InterviewsRepository : CrudRepository<Interview, Int>