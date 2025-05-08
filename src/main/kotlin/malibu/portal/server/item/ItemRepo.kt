package malibu.portal.server.item

//import io.micronaut.data.runtime.criteria.where
import com.querydsl.jpa.impl.JPAQuery
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import jakarta.persistence.EntityManager
import malibu.portal.operate.dto.item.ItemSearchSpec
import malibu.portal.server.entity.Item
import malibu.portal.server.entity.QItem
import malibu.portal.server.entity.QItemExtraLink
import malibu.portal.server.entity.QItemTag
import java.util.*

@Repository
abstract class ItemRepo(
    private val entityManater: EntityManager
): JpaRepository<Item, UUID>
{
    private val qItem = QItem.item
    private val qItemExtraLink = QItemExtraLink.itemExtraLink
    private val qItemTag = QItemTag.itemTag

    /**
     * HHH90003004 에러 관련 참고: https://twosky.tistory.com/60
     */
    fun search(searchSpec: ItemSearchSpec, pageable: Pageable): Page<Item> {
        val query = JPAQuery<Item>(entityManater)
            .from(qItem)
            .leftJoin(qItem.mutableLinks, qItemExtraLink)
            .leftJoin(qItem.mutableTags, qItemTag)

        searchSpec.keyword?.also { keyword ->
            query.where(
                qItem.name.like("%$keyword%")
                    .or(qItem.representLink.like("%$keyword%"))
                    .or(qItemExtraLink.name.like("%$keyword%"))
                    .or(qItemExtraLink.url.like("%$keyword%"))
            )
        }
//        searchSpec.itemId?.also { itemId ->
//            query.where(qItem.id.(itemId))
//        }
        searchSpec.name?.also { name ->
            query.where(qItem.name.like("%$name%")
                .or(qItemExtraLink.name.like("%$name%"))
            )
        }
        searchSpec.url?.also { url ->
            query.where(qItemExtraLink.url.like("%$url%"))
        }
        searchSpec.tagIds?.also { tagIds ->
            query.where(qItemTag.tag.id.`in`(tagIds))
        }

        val total = query.select(qItem.countDistinct()).fetchOne()!!
        val selectedIds = query.select(qItem.id)
            .offset(pageable.offset)
            .limit(pageable.size.toLong())
            .fetch()

        val results = JPAQuery<Item>(entityManater)
            .from(qItem)
            .select(qItem)
//            .leftJoin(qItem.mutableLinks, qItemExtraLink).fetchJoin()
            .where(qItem.id.`in`(selectedIds))
            .fetch()

        return Page.of(results, pageable, total)
    }
}