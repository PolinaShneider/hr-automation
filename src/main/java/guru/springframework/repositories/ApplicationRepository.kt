package guru.springframework.repositories

import guru.springframework.domain.entities.Application
import org.springframework.data.repository.CrudRepository

interface ApplicationRepository : CrudRepository<Application, Int>