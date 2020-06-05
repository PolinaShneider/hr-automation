package guru.springframework.services.interviewer

import guru.springframework.domain.entities.*
import guru.springframework.repositories.InterviewersRepository
import guru.springframework.repositories.InterviewsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InterviewerServiceImpl : InterviewerService {
    private var interviewersRepository: InterviewersRepository? = null
    private var interviewsRepository: InterviewsRepository? = null

    @Autowired
    fun setRepository(interviewersRepository: InterviewersRepository) {
        this.interviewersRepository = interviewersRepository
    }

    override fun getAvailableInterviewer(): Interviewer {
        return interviewersRepository!!.findAll().first()
    }

    override fun conductInterview(id: Int?): Interview {
        val interview = interviewsRepository!!.findOne(id)
        interview.status = Status.INTERVIEW_PASSED
        return interviewsRepository!!.save(interview)
    }

    override fun giveFeedback(interview: Interview): Interview {
        interview.status = Status.INTERVIEW_PASSED
        return interviewsRepository!!.save(interview)
    }

    override fun getInterviewer(id: Int): Interviewer? {
        return this.interviewersRepository!!.findOne(id)
    }
}
