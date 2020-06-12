package guru.springframework.domain.entities

import javax.persistence.*

@Entity
class Candidate(override var fullName: String = "", val experience: String = "", var worksInCompany: Boolean = false): User(fullName) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null

    fun notifyMe(status: Status) {
        when (status) {
            Status.INTERVIEW_UPCOMING -> println("For candidate: Hey-hey, ${this.fullName}, you gonna have an interview")
            Status.OFFER -> println("For candidate: ${this.fullName} you've got offer")
            Status.REJECT -> println("For candidate: Sorry to hear that, ${this.fullName}, reject")
        }
    }
}
