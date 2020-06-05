package guru.springframework.events

import guru.springframework.domain.model.Application
import org.springframework.context.ApplicationEvent


class ApplicationCreatedEvent(source: Any, val eventType: String, val employee: Application) : ApplicationEvent(source) {
    companion object {
        private val serialVersionUID = 1L
    }
}