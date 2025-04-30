package malibu.portal

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import malibu.portal.entity.Tag
import java.util.*

@Repository
abstract class TagRepo: JpaRepository<Tag, UUID> {

    abstract fun findIdByNameLike(name: String, pageable: Pageable): Page<UUID>
}