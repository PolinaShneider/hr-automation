package guru.springframework.repositories

import guru.springframework.domain.model.Application
import org.springframework.data.repository.CrudRepository

interface ApplicationRepository : CrudRepository<Application, Int>