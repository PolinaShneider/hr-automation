package guru.springframework.domain.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
    var candidateId: Int? = null
    var positionId: Int? = null
    var status: Status = Status.INTERVIEW_UPCOMING
    var feedback: Feedback = Feedback.NONE
}

enum class Feedback {
    ACCEPTED,
    FAIL,
    NONE
}