package guru.springframework.services.candidate

import guru.springframework.domain.entities.*


interface CandidateService {
    fun notifyMe(candidateId: Int, status: Status)
}
