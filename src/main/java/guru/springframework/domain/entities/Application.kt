package guru.springframework.domain.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
    var candidateId: Int? = null
    var positionId: Int? = null
    var status: Status = Status.DRAFT
    var interviewId: Int? = null
}

enum class Status {
    DRAFT,
    PENDING,
    INTERVIEW_UPCOMING,
    INTERVIEW_PASSED,
    OFFER,
    REJECT
}
