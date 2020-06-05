package guru.springframework.services.interview

import guru.springframework.domain.entities.Interview
import guru.springframework.repositories.InterviewsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InterviewServiceImpl : InterviewService {
    private var interviewsRepository: InterviewsRepository? = null

    @Autowired
    fun setRepository(interviewsRepository: InterviewsRepository) {
        this.interviewsRepository = interviewsRepository
    }

    override fun update(interview: Interview): Interview {
        return this.interviewsRepository!!.save(interview)
    }

    override fun getAllByIds(ids: Iterable<Int>): Iterable<Interview> {
        return this.interviewsRepository!!.findAll(ids)
    }

    override fun getInterview(id: Int): Interview? {
        return this.interviewsRepository!!.findOne(id)
    }
}
