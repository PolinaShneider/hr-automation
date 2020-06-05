package guru.springframework.controllers

import guru.springframework.domain.entities.Application
import guru.springframework.domain.entities.Status
import guru.springframework.services.application.ApplicationService
import guru.springframework.services.hr.HrService
import guru.springframework.services.position.PositionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class CandidateController {
    private var positionService: PositionService? = null
    private var applicationService: ApplicationService? = null
    private var hrService: HrService? = null

    @Autowired
    fun setProductService(
        positionService: PositionService,
        applicationService: ApplicationService,
        hrService: HrService
    ) {
        this.applicationService = applicationService
        this.positionService = positionService
        this.hrService = hrService
    }

    @RequestMapping(value = ["/candidate/"])
    fun index(): String {
        return "candidate-index"
    }

    @RequestMapping(value = ["/candidate/positions"], method = [RequestMethod.GET])
    fun list(model: Model): String {
        model.addAttribute("positions", positionService!!.listOpenPositions())
        return "candidate/positions"
    }

    @RequestMapping("/candidate/position/{id}")
    fun showPosition(@PathVariable id: Int?, model: Model): String {
        model.addAttribute("position", positionService!!.getPositionById(id))
        return "candidate/positionshow"
    }

    @RequestMapping("/candidate/position/{id}/apply")
    fun newPositions(@PathVariable id: Int?, model: Model): String {
        model.addAttribute("positionIds", positionService!!.listAllPositions().map { it.id })
        model.addAttribute("selectedPositionId", id)
        model.addAttribute("app", Application())
        return "candidate/newapplication"
    }

    @RequestMapping(value = ["/candidate/application"], method = [RequestMethod.POST])
    fun newApplication(application: Application): String {

        application.status = Status.PENDING
        applicationService!!.saveApplication(application)

        hrService!!.notifyMe(application.status, application.id!!)

        return "redirect:/candidate/application/" + application.id!!
    }

    @RequestMapping("/candidate/application/{id}")
    fun showApplication(@PathVariable id: Int?, model: Model): String {
        model.addAttribute("app", applicationService!!.getApplicationById(id))
        return "candidate/applicationshow"
    }

    @RequestMapping("/candidate/applications")
    fun showApplications(model: Model): String {
        model.addAttribute("apps", applicationService!!.getApplicationByCandidateId(1))
        return "candidate/applications"
    }
}
