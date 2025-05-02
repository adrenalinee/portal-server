package malibu.portal.server.tag

import com.querydsl.jpa.impl.JPAQuery
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import jakarta.persistence.EntityManager
import malibu.portal.entity.QTag
import malibu.portal.entity.Tag
import java.util.*

@Repository
abstract class TagRepo(
    private val entityManater: EntityManager
): JpaRepository<Tag, UUID> {
    private val qTag = QTag.tag

    fun findIdByNames(tagNames: List<String>, pageable: Pageable): Page<UUID> {
        val query = JPAQuery<Tag>(entityManater)
            .from(qTag)
            .where(
                qTag.name.`in`(tagNames)
            )

        val total = query.select(qTag.countDistinct()).fetchOne()!!
        val results = JPAQuery<Tag>(entityManater)
            .from(qTag)
            .select(qTag.id)
            .where(qTag.name.`in`(tagNames))
            .fetch()

        return Page.of(results, pageable, total)
    }
}