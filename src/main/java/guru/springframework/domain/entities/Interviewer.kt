package guru.springframework.domain.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Interviewer(fullName: String = ""): User(fullName) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
    var upcomingInterviewsIDs: Int? = null

    fun notifyMe(action: INTERVIEWER_ACTION, actionId: Int?) {
        if (action == INTERVIEWER_ACTION.SHOULD_CONDUCT_INTERVIEW) {
            this.upcomingInterviewsIDs = actionId
        }
    }
}

enum class INTERVIEWER_ACTION {
    SHOULD_CONDUCT_INTERVIEW,
    CANDIDATE_ACCEPTED_OFFER
}
