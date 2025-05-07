package malibu.portal.server

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller
class HelloController {

//    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/hello")
    fun hello(): String {
        return "Hello World"
    }
}