package malibu.portal.server.item

import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import malibu.portal.entity.Item
import malibu.portal.operate.ServiceOperation
import malibu.portal.operate.dto.item.*
import malibu.portal.server.exception.ItemNotFoundException
import malibu.portal.server.tag.TagRepo
import java.util.*

@Singleton
class ItemService(
    private val itemRepo: ItemRepo,
    private val tagRepo: TagRepo,
): ServiceOperation<UUID, ItemDto, ItemDtoSimple, ItemSearchSpec, ItemCreateSpec, ItemUpdateSpec> {
    companion object {
        const val MAX_TAG_SIZE = 50
    }


    @Transactional
    override fun create(createSpec: ItemCreateSpec): ItemDto {
        return itemRepo.saveAndFlush(
            Item.create(
                organizationId = "1234",
                createSpec = createSpec
            )
        ).toDto()
    }

    @Transactional(readOnly = true)
    override fun search(
        searchSpec: ItemSearchSpec,
        pageable: Pageable
    ): Page<ItemDtoSimple> {
        val newSearchSpec = searchSpec.tagNames?.let { tagNames ->
            val tagIdPage = tagRepo.findIdByNames(tagNames, Pageable.from(0, MAX_TAG_SIZE))
            searchSpec.copy(
                tagIds = tagIdPage.content
            )
        }?: searchSpec

        return itemRepo.search(newSearchSpec, pageable).map { it.toDtoSimple() }
    }

    @Transactional(readOnly = true)
    override fun getOne(id: UUID): ItemDto? {
        return itemRepo.findById(id)
            .map { it.toDto() }
            .orElse(null)
    }

    @Transactional
    override fun update(id: UUID, updateSpec: ItemUpdateSpec): ItemDto {
        val item = itemRepo.findById(id)
            .orElseThrow { ItemNotFoundException(id) }

        item.update(updateSpec)

        return itemRepo.saveAndFlush(item).toDto()
    }

    @Transactional
    override fun delete(id: UUID) {
        val item = itemRepo.findById(id)
            .orElseThrow { ItemNotFoundException(id) }

        itemRepo.delete(item)
    }
}