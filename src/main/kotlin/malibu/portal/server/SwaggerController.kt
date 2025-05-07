package malibu.portal.server

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Options

//@Controller
class SwaggerController {

    @Get("/swagger{/path}")
    fun optionsSwagger(path: String?, request: HttpRequest<*>): HttpResponse<String> {
        return HttpResponse.ok("")
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, OPTIONS")
            .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
    }
}