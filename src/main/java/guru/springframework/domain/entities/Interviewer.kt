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

    fun notifyMe(interviewId: Int?) {
        this.upcomingInterviewsIDs = interviewId
        println("For Interviewer: You have to conduct interview")
    }
}

