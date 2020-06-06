package guru.springframework.builder

import guru.springframework.domain.entities.Application
import guru.springframework.domain.entities.Status
import org.springframework.stereotype.Component

@Component
class ApplicationBuilder {
    fun create(
        positionId: Int,
        candidateId: Int,
        status: Status = Status.DRAFT
    ): Application {
        val application = Application()
        application.status = status
        application.positionId = positionId
        application.candidateId = candidateId

        return application
    }
}