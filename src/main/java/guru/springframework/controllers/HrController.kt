package guru.springframework.controllers

import guru.springframework.domain.model.Application
import guru.springframework.domain.model.Position
import guru.springframework.domain.model.Status
import guru.springframework.services.application.ApplicationService
import guru.springframework.services.position.PositionService
import guru.springframework.services.team.TeamService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class HrController {
    private var positionService: PositionService? = null
    private var teamService: TeamService? = null
    private var applicationService: ApplicationService? = null

    @Autowired
    fun setProductService(
        positionService: PositionService,
        teamService: TeamService,
        applicationService: ApplicationService
    ) {
        this.teamService = teamService
        this.positionService = positionService
        this.applicationService = applicationService
    }

    @RequestMapping(value = ["/hr/"])
    fun index(): String {
        return "hr-index"
    }

    @RequestMapping(value = ["/hr/positions"], method = [RequestMethod.GET])
    fun listPositions(model: Model): String {
        model.addAttribute("positions", positionService!!.listAllPositions())
        return "hr/positions"
    }

    @RequestMapping(value = ["/hr/applications"], method = [RequestMethod.GET])
    fun listApplications(model: Model): String {
        model.addAttribute("apps", applicationService!!.listAllApplications())
        return "hr/applications"
    }

    @RequestMapping(value = ["/hr/application/{id}"])
    fun showApplication(@PathVariable id: Int?, model: Model): String {
        model.addAttribute("app", applicationService!!.getApplicationById(id))
        return "hr/applicationshow"
    }

    @RequestMapping(value = ["/hr/application/{id}/edit"])
    fun editApplication(@PathVariable id: Int?, model: Model): String {
        model.addAttribute("statuses", Status.values())
        model.addAttribute("app", applicationService!!.getApplicationById(id))
        return "hr/applicationform"
    }

    @RequestMapping("/hr/position/{id}")
    fun showPosition(@PathVariable id: Int?, model: Model): String {
        model.addAttribute("position", positionService!!.getPositionById(id))
        return "hr/positionshow"
    }

    @RequestMapping("/hr/position/{id}/edit")
    fun edit(@PathVariable id: Int?, model: Model): String {
        val teamsIds = teamService?.listAllTeams()?.map { it.id }

        model.addAttribute("teamsIds", teamsIds)
        model.addAttribute("position", positionService!!.getPositionById(id))
        return "hr/positionform"
    }

    @RequestMapping("/hr/position/new")
    fun newPositions(model: Model): String {
        val teamsIds = teamService?.listAllTeams()?.map { it.id }

        model.addAttribute("teamsIds", teamsIds)
        model.addAttribute("position", Position())
        return "hr/newposition"
    }

    @RequestMapping(value = ["/hr/position"], method = [RequestMethod.POST])
    fun savePosition(position: Position): String {

        positionService!!.savePosition(position)

        return "redirect:/hr/position/" + position.id!!
    }

    @RequestMapping(value = ["/hr/application"], method = [RequestMethod.POST])
    fun saveApplication(application: Application): String {

        applicationService!!.saveApplication(application)

        return "redirect:/hr/application/" + application.id!!
    }
}