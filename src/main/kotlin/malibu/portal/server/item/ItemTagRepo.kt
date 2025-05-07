package malibu.portal.server.item

import com.querydsl.jpa.impl.JPAQuery
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import jakarta.persistence.EntityManager
import malibu.portal.operate.dto.item.ItemTagSearchSpec
import malibu.portal.server.entity.Item
import malibu.portal.server.entity.ItemTag
import malibu.portal.server.entity.QItemTag
import malibu.portal.server.entity.QTag

@Repository
abstract class ItemTagRepo(
    private val entityManater: EntityManager
): JpaRepository<ItemTag, Long> {
    private val qItemTag = QItemTag.itemTag
    private val qTag = QTag.tag

    fun search(item: Item, searchSpec: ItemTagSearchSpec, pageable: Pageable): Page<ItemTag> {
        val query = JPAQuery<ItemTag>(entityManater)
            .from(qItemTag)
            .where(qItemTag.item.eq(item))

        val total = query.select(qItemTag.countDistinct()).fetchOne()!!
        val results = query
            .select(qItemTag)
            .innerJoin(qItemTag.tag, qTag).fetchJoin()
            .offset(pageable.offset)
            .limit(pageable.size.toLong())
            .fetch()

        return Page.of(results, pageable, total)
    }
}