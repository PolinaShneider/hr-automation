package guru.springframework.events

import guru.springframework.services.position.PositionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component


@Component
class UserEventsProcessor : ApplicationListener<ApplicationCreatedEvent> {
    private var positionService: PositionService? = null

    @Autowired
    fun setApplicationRepository(
        positionService: PositionService
    ) {
        this.positionService = positionService
    }

    override fun onApplicationEvent(event: ApplicationCreatedEvent) {
        this.positionService!!.notifyMe(event.employee.status)
    }
}