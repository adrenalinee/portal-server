package malibu.portal.operate

import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable

interface BaseOperation<ID, R, S, C, U> {
    suspend fun create(createSpec: C): R
    suspend fun search(searchSpec: S?, pageable: Pageable): Page<R>
    suspend fun getOne(id: ID): R?
    suspend fun update(id: ID, updateSpec: U): R
    suspend fun delete(id: ID): R
}