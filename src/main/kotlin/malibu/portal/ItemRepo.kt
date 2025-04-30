package malibu.portal

//import io.micronaut.data.runtime.criteria.where
import com.querydsl.jpa.impl.JPAQuery
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import jakarta.persistence.EntityManager
import malibu.portal.entity.Item
import malibu.portal.entity.QItem
import malibu.portal.entity.QItemTag
import malibu.portal.entity.QSubItem
import malibu.portal.operate.dto.item.ItemSearchSpec
import java.util.*

@Repository
abstract class ItemRepo(
    private val entityManater: EntityManager
): JpaRepository<Item, UUID>
{
    private val qItem = QItem.item
    private val qSubItem = QSubItem.subItem
    private val qItemTag = QItemTag.itemTag

    fun search2(searchSpec: ItemSearchSpec, pageable: Pageable): Page<Item> {
        val query = JPAQuery<Item>(entityManater)
            .from(qItem)

        searchSpec.name?.also { name ->
            query.where(
                qItem.name.like("%$name%")
//                    .or(qSubItem.name.like("%$name%"))
            )
        }
        searchSpec.url?.also { url ->
            query.where(qItem.url.like("%$url%"))
        }

        val total = query.select(qItem.countDistinct()).fetchOne()!!
        val selectedIds = query.select(qItem.id)
            .offset(pageable.offset)
            .limit(pageable.size.toLong())
            .fetch()

        val results = JPAQuery<Item>(entityManater)
            .from(qItem)
            .select(qItem)
            .leftJoin(qItem.mutableChildren, qSubItem).fetchJoin()
            .where(qItem.id.`in`(selectedIds))
            .fetch()

        return Page.of(results, pageable, total)
    }


    fun search(searchSpec: ItemSearchSpec, pageable: Pageable): Page<Item> {
        val query = JPAQuery<Item>(entityManater)
            .from(qItem)
            .leftJoin(qItem.mutableChildren, qSubItem)

        searchSpec.name?.also { name ->
            query.where(
                qItem.name.like("%$name%")
                    .or(qSubItem.name.like("%$name%"))
            )
        }
        searchSpec.url?.also { url ->
            query.where(qItem.url.like("%$url%"))
        }

        val total = query.select(qItem.countDistinct()).fetchOne()!!
        val results = query.select(qItem)
            .fetchJoin()
            .offset(pageable.offset)
            .limit(pageable.size.toLong())
            .fetch()

        return Page.of(results, pageable, total)
    }
}