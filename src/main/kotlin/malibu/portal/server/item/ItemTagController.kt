package malibu.portal.server.item

import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import jakarta.validation.Valid
import malibu.portal.operate.dto.item.ItemTagCreateSpec
import malibu.portal.operate.dto.item.ItemTagDto
import malibu.portal.operate.dto.item.ItemTagSearchSpec
import java.util.UUID

@Controller("/items/{itemId}/tags")
class ItemTagController(
    private val itemTagService: ItemTagService,
) {

    @Post
    fun create(
        itemId: UUID,
        @Valid @Body createSpec: ItemTagCreateSpec
    ): ItemTagDto {
        return itemTagService.create(itemId, createSpec)
    }

    @Get("{?searchSpec*}")
    fun search(
        itemId: UUID,
        @Valid searchSpec: ItemTagSearchSpec,
        pageable: Pageable,
    ): Page<ItemTagDto> {
        return itemTagService.search(itemId, searchSpec, pageable)
    }
}