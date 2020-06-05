package guru.springframework.services.application

import guru.springframework.domain.entities.Application
import guru.springframework.repositories.ApplicationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.context.ApplicationEventPublisher



@Service
class ApplicationServiceImpl : ApplicationService {
    private var applicationRepository: ApplicationRepository? = null
    private var publisher: ApplicationEventPublisher? = null

    @Autowired
    fun setApplicationRepository(
        applicationRepository: ApplicationRepository,
        publisher: ApplicationEventPublisher
    ) {
        this.applicationRepository = applicationRepository
        this.publisher = publisher;
    }

    override fun listAllApplications(): Iterable<Application> {
        return applicationRepository!!.findAll()
    }

    override fun getApplicationByCandidateId(id: Int?): Iterable<Application> {
        return applicationRepository!!.findAll().filter {
            it.candidateId == id
        }
    }

    override fun getApplicationById(id: Int?): Application {
        return applicationRepository!!.findOne(id)
    }

    override fun saveApplication(application: Application): Application {
        return applicationRepository!!.save(application)
    }
}
