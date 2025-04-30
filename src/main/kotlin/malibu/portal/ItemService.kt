package malibu.portal

import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import malibu.portal.entity.Item
import malibu.portal.operate.dto.item.ItemCreateSpec
import malibu.portal.operate.dto.item.ItemDto
import malibu.portal.operate.dto.item.ItemSearchSpec

@Singleton
open class ItemService(
    private val itemRepo: ItemRepo,
    private val tagRepo: TagRepo,
) {
    @Transactional
    open fun create(createSpec: ItemCreateSpec): ItemDto {
        return itemRepo.saveAndFlush(
            Item.create(
                organizationId = "1234",
                createSpec = createSpec
            )
        ).toDto()
    }

    @Transactional(readOnly = true)
    open fun search(
        searchSpec: ItemSearchSpec,
        pageable: Pageable
    ): Page<ItemDto> {

        return itemRepo.search2(searchSpec, pageable).map { it.toDto() }
    }
}