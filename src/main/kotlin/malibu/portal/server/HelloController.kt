package malibu.portal.server

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Controller
class HelloController {

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/hello")
    fun hello(): String {
        return "Hello World"
    }
}