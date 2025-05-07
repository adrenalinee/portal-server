package malibu.portal.operate

import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.Body
import jakarta.validation.Valid

interface DomainOperation<ID, R, RS, S, C, U> {
    fun create(@Valid @Body createSpec: C): R
    fun search(@Valid searchSpec: S, pageable: Pageable): Page<RS>
    fun getOne(id: ID): R?
    fun update(id: ID, @Valid @Body updateSpec: U): R
    fun delete(id: ID)
}