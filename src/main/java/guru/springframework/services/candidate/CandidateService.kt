package guru.springframework.services.candidate

import guru.springframework.domain.entities.*


interface CandidateService {
    fun getCandidateName(candidateId: Int): String

    fun worksInCompany(candidateId: Int?): Boolean

    fun notifyMe(candidateId: Int, status: Status)
}
