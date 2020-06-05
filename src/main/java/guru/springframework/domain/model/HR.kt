package guru.springframework.domain.model

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class HR(fullName: String): User(fullName) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
}
