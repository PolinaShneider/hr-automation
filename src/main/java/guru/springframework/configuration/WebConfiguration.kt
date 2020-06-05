package guru.springframework.configuration

import org.h2.server.web.WebServlet
import org.springframework.boot.context.embedded.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class WebConfiguration {
    @Bean
    internal open fun h2servletRegistration(): ServletRegistrationBean {
        val registrationBean = ServletRegistrationBean(WebServlet())
        registrationBean.addUrlMappings("/console/*")
        return registrationBean
    }
}
