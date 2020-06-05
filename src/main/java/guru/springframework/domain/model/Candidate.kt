package guru.springframework.domain.model

import javax.persistence.*

@Entity
class Candidate(fullName: String, val experience: String): User(fullName) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
}
