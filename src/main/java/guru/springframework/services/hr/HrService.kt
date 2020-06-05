package guru.springframework.services.hr

import guru.springframework.domain.entities.*


interface HrService {
    fun getAvailableInterviewer(): Interviewer?

    fun notifyMe(status: Status, applicationId: Int, feedback: Feedback = Feedback.NONE)

    fun scheduleInterview(candidateId: Int, positionId: Int): Interview

    fun updateApplication(applicationId: Int, status: Status): Application
}
