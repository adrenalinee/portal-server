package malibu.portal.server.item

import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Patch
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import jakarta.validation.Valid
import malibu.portal.operate.dto.item.ItemCreateSpec
import malibu.portal.operate.dto.item.ItemDto
import malibu.portal.operate.dto.item.ItemDtoSimple
import malibu.portal.operate.dto.item.ItemSearchSpec
import malibu.portal.operate.dto.item.ItemUpdateSpec
import java.util.UUID

@Validated
@Controller("/items")
class ItemController(
    private val itemService: ItemService
) {
    @Post
    fun create(
        @Valid @Body createSpec: ItemCreateSpec
    ): ItemDto {
        return itemService.create(createSpec)
    }

    @Get("{?searchSpec*}")
    fun findAll(
        @Valid searchSpec: ItemSearchSpec,
        pageable: Pageable
    ): Page<ItemDtoSimple> {
        return itemService.search(searchSpec, pageable)
    }

    @Get("/{itemId}")
    fun getOne(itemId: UUID): ItemDto? {
        return itemService.getOne(itemId)
    }

    @Patch("{itemId}")
    fun update (
        itemId: UUID,
        @Valid @Body updateSpec: ItemUpdateSpec
    ): ItemDto {
        return itemService.update(itemId, updateSpec)
    }

    @Delete("{itemId}")
    fun delete(itemId: UUID) {
        itemService.delete(itemId)
    }
}