package guru.springframework.services.candidate

import guru.springframework.domain.entities.Status
import guru.springframework.repositories.ApplicationRepository
import guru.springframework.repositories.CandidateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CandidateServiceImpl : CandidateService {
    private var candidateRepository: CandidateRepository? = null
    private var applicationsRepository: ApplicationRepository? = null

    @Autowired
    fun setRepository(
        candidateRepository: CandidateRepository,
        applicationsRepository: ApplicationRepository
    ) {
        this.candidateRepository = candidateRepository
        this.applicationsRepository = applicationsRepository
    }

    override fun notifyMe(candidateId: Int, status: Status) {
        val candidate = this.candidateRepository!!.findOne(candidateId) ?: throw Exception("No such candidate")
        candidate.notifyMe(status)
    }

    override fun getCandidateName(candidateId: Int): String {
        return this.candidateRepository!!.findOne(candidateId).fullName
    }
}
