package guru.springframework.builder

import guru.springframework.domain.entities.*
import org.springframework.stereotype.Component

@Component
class InterviewBuilder {
    fun create(
        candidateId: Int,
        positionId: Int,
        status: Status = Status.INTERVIEW_UPCOMING
    ): Interview {
        val interview = Interview()
        interview.status = status
        interview.positionId = positionId
        interview.candidateId = candidateId
        return interview
    }
}