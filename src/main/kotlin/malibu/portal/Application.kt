package malibu.portal

import io.micronaut.http.HttpMethod
import io.micronaut.http.server.cors.CrossOrigin
import io.micronaut.runtime.Micronaut.run
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info


//@CrossOrigin(
//    allowedOrigins = ["*"],
//    allowedMethods=[HttpMethod.GET, HttpMethod.POST, HttpMethod.PATCH, HttpMethod.DELETE]
//)
@OpenAPIDefinition(
    info = Info(
        title = "portal api",
        version = "v1"
    )
)
object Application


fun main(args: Array<String>) {
    run(*args)
}

