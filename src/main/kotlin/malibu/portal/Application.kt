package malibu.portal

import io.micronaut.runtime.Micronaut.run
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import malibu.portal.init.InitDataService

@OpenAPIDefinition(
    info = Info(
        title = "portal api",
        version = "v1"
    )
)
object Application


fun main(args: Array<String>) {
    run(*args)
        .getBean(InitDataService::class.java)
        .init()
}

