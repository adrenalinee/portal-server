package malibu.portal.operate

import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable

interface ServiceOperation<ID, R, RS, S, C, U> {
    fun create(createSpec: C): R
    fun search(searchSpec: S, pageable: Pageable): Page<RS>
    fun getOne(id: ID): R?
    fun update(id: ID, updateSpec: U): R
    fun delete(id: ID)
}