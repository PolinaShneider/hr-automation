package guru.springframework.services.hr

import guru.springframework.domain.entities.*
import guru.springframework.repositories.ApplicationRepository
import guru.springframework.repositories.InterviewersRepository
import guru.springframework.repositories.InterviewsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HrServiceImpl : HrService {
    private var interviewersRepository: InterviewersRepository? = null
    private var interviewsRepository: InterviewsRepository? = null
    private var applicationsRepository: ApplicationRepository? = null

    @Autowired
    fun setRepository(
        interviewersRepository: InterviewersRepository,
        interviewsRepository: InterviewsRepository,
        applicationsRepository: ApplicationRepository
    ) {
        this.interviewersRepository = interviewersRepository
        this.interviewsRepository = interviewsRepository
        this.applicationsRepository = applicationsRepository
    }

    override fun getAvailableInterviewer(): Interviewer? {
        return interviewersRepository!!.findAll().first()
    }

    override fun notifyMe(status: Status, applicationId: Int, feedback: Feedback) {
        if (status == Status.INTERVIEW_PASSED) {
            val application = applicationsRepository!!.findOne(applicationId)
            if (feedback == Feedback.ACCEPTED) {
                application.status = Status.OFFER
            } else if (feedback == Feedback.FAIL) {
                application.status = Status.REJECT
            }

            applicationsRepository!!.save(application)

            println("For HR: Interview have passed, go to check feedback for application $applicationId")
        } else if (status == Status.PENDING) {
            println("For HR: There is a new application")
        }
    }

    override fun scheduleInterview(candidateId: Int, positionId: Int): Interview {
        val interview = Interview()
        interview.candidateId = candidateId
        interview.positionId = positionId
        return this.interviewsRepository!!.save(interview)
    }

    override fun updateApplication(applicationId: Int, status: Status): Application {
        val application = this.applicationsRepository!!.findOne(applicationId)
        application.status = status
        return this.applicationsRepository!!.save(application)
    }
}
