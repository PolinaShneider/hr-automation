package guru.springframework.services.application

import guru.springframework.domain.entities.Application


interface ApplicationService {
    fun listAllApplications(): Iterable<Application>

    fun getApplicationById(id: Int?): Application

    fun getApplicationByCandidateId(id: Int?): Iterable<Application>

    fun saveApplication(application: Application): Application
}
