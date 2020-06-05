package guru.springframework.domain.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Team(val teamName: String = "") {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
}
