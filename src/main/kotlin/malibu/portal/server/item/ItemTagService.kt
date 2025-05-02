package malibu.portal.server.item

import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import malibu.portal.entity.ItemTag
import malibu.portal.operate.dto.item.ItemTagCreateSpec
import malibu.portal.operate.dto.item.ItemTagDto
import malibu.portal.operate.dto.item.ItemTagSearchSpec
import malibu.portal.server.exception.ItemNotFoundException
import malibu.portal.server.exception.TagNotFoundException
import malibu.portal.server.tag.TagRepo
import java.util.*

@Singleton
class ItemTagService(
    private val itemRepo: ItemRepo,
    private val tagRepo: TagRepo,
    private val itemTagRepo: ItemTagRepo,
) {

    @Transactional
    fun create(itemId: UUID, createSpec: ItemTagCreateSpec): ItemTagDto {
        val item = itemRepo.findById(itemId)
            .orElseThrow { ItemNotFoundException(itemId) }

        val tagId = createSpec.tagId
        val tag = tagRepo.findById(tagId)
            .orElseThrow { TagNotFoundException(tagId) }

        return itemTagRepo.saveAndFlush(
            ItemTag.create(
                item = item,
                tag = tag
            )
        ).toDto()
    }

    @Transactional(readOnly = true)
    fun search(
        itemId: UUID,
        searchSpec: ItemTagSearchSpec,
        pageable: Pageable
    ): Page<ItemTagDto> {
        val item = itemRepo.findById(itemId)
            .orElseThrow { ItemNotFoundException(itemId) }

        return itemTagRepo.search(item, searchSpec, pageable).map { it.toDto() }
    }
}