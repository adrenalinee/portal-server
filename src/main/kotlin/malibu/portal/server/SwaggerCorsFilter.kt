package malibu.portal.server

import io.micronaut.core.annotation.Order
import io.micronaut.core.order.Ordered
import io.micronaut.http.HttpMethod
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Filter
import io.micronaut.http.annotation.RequestFilter
import io.micronaut.http.annotation.ServerFilter
import io.micronaut.http.filter.HttpServerFilter
import io.micronaut.http.filter.ServerFilterChain
import io.micronaut.http.filter.ServerFilterPhase
import io.micronaut.http.server.cors.CorsFilter
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import java.util.concurrent.CompletableFuture

//@ServerFilter("/swagger/**")
//@Filter("/swagger/**")
//class SwaggerCorsFilter: HttpServerFilter {
//    override fun doFilter(
//        request: HttpRequest<*>,
//        chain: ServerFilterChain
//    ): Publisher<MutableHttpResponse<*>> {
//        println("swagger filter")
//        return Flux.from(chain.proceed(request))
//            .map { response ->
//                if (request.method == HttpMethod.OPTIONS) {
//                    response.header("Access-Control-Allow-Origin", "*")
//                    response.header("Access-Control-Allow-Methods", "GET")
//                    response.header("Access-Control-Allow-Headers", "Content-Type, Authorization")
////                    response.header("Access-Control-Max-Age", "3600")
//                }
//
//                response
//            }
//    }
//}

//@Order(Ordered.HIGHEST_PRECEDENCE)
//@ServerFilter("/swagger/**")
//class SwaggerCorsFilter: Ordered {
//
//    @RequestFilter
//    fun filter(request: HttpRequest<*>): CompletableFuture<HttpResponse<*>> {
//        println("swagger filter")
//
//        return CompletableFuture.completedFuture(null)
//    }
//
//    override fun getOrder(): Int {
//        return ServerFilterPhase.METRICS.before()
//    }
//}