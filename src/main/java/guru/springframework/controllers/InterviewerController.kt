package guru.springframework.controllers

import guru.springframework.domain.entities.*
import guru.springframework.services.application.ApplicationService
import guru.springframework.services.candidate.CandidateService
import guru.springframework.services.hr.HrService
import guru.springframework.services.interview.InterviewService
import guru.springframework.services.interviewer.InterviewerService
import guru.springframework.services.position.PositionService
import guru.springframework.services.team.TeamService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class InterviewerController {
    private var interviewerService: InterviewerService? = null
    private var interviewService: InterviewService? = null
    private var applicationService: ApplicationService? = null
    private var candidateService: CandidateService? = null
    private var hrService: HrService? = null

    @Autowired
    fun setService(
        interviewerService: InterviewerService,
        interviewService: InterviewService,
        hrService: HrService,
        candidateService: CandidateService,
        applicationService: ApplicationService
    ) {
        this.interviewService = interviewService
        this.interviewerService = interviewerService
        this.hrService = hrService
        this.candidateService = candidateService
        this.applicationService = applicationService
    }

    @RequestMapping(value = ["/interviewer/"])
    fun index(): String {
        return "interviewer-index"
    }

    @RequestMapping(value = ["/interviewer/interviews"], method = [RequestMethod.GET])
    fun listPositions(model: Model): String {
        val interviewId = interviewerService!!.getInterviewer(1)?.upcomingInterviewsIDs
        model.addAttribute("interviews", interviewService!!.getInterview(interviewId!!))
        return "interviewer/interviews"
    }

    @RequestMapping(value = ["/interviewer/interview/{id}"])
    fun showApplication(@PathVariable id: Int?, model: Model): String {
        model.addAttribute("interview", interviewService!!.getInterview(id!!))
        return "interviewer/interviewshow"
    }

    @RequestMapping(value = ["/interviewer/interview/{id}/edit"])
    fun editApplication(@PathVariable id: Int?, model: Model): String {
        model.addAttribute("statuses", Status.values())
        model.addAttribute("feedbacks", Feedback.values())
        model.addAttribute("interview", interviewService!!.getInterview(id!!))
        return "interviewer/interviewform"
    }

    @RequestMapping(value = ["/interviewer/interview"], method = [RequestMethod.POST])
    fun updateInterview(interview: Interview): String {

        val applicationId = applicationService!!.listAllApplications().first {
            it.candidateId == interview.candidateId && it.positionId == interview.positionId
        }.id

        if (interview.status == Status.INTERVIEW_PASSED) {
            hrService!!.notifyMe(interview.status, applicationId!!, interview.feedback)
        }

        interviewService!!.update(interview)

        return "redirect:/interviewer/interview/" + interview.id!!
    }
}
