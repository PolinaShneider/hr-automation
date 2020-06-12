package guru.springframework.builder

import guru.springframework.domain.entities.Candidate
import guru.springframework.domain.entities.HR
import guru.springframework.domain.entities.Interviewer
import guru.springframework.domain.entities.Position
import org.springframework.stereotype.Component

@Component
class UserBuilder {
    fun createInterviewer(
        fullName: String,
        upcomingInterviewId: Int?
    ): Interviewer {
        val interviewer = Interviewer(fullName)

        if (upcomingInterviewId != null) {
            interviewer.upcomingInterviewsIDs = upcomingInterviewId
        }

        return interviewer
    }

    fun createCandidate(
        fullName: String,
        experience: String,
        worksInCompany: Boolean
    ): Candidate {
        return Candidate(fullName, experience, worksInCompany)
    }

    fun createHr(
        fullName: String
    ): HR {
        return HR(fullName)
    }
}