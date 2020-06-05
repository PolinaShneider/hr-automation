package guru.springframework.services.interviewer

import guru.springframework.domain.entities.Interview
import guru.springframework.domain.entities.Interviewer


interface InterviewerService {
    fun getAvailableInterviewer(): Interviewer

    fun conductInterview(id: Int?): Interview

    fun giveFeedback(interview: Interview): Interview

    fun getInterviewer(id: Int): Interviewer?
}
