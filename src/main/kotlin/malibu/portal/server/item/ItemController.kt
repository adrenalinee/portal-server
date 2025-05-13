package malibu.portal.server.item

import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import jakarta.validation.Valid
import malibu.portal.operate.dto.item.*
import java.util.*

@Validated
@Controller("/items")
class ItemController(
    private val itemService: ItemService
) {
    @Post
    @Status(HttpStatus.CREATED)
    fun create(
//        authentication: Authentication,
        @Valid @Body createSpec: ItemCreateSpec
    ): ItemDto {
//        println(authentication.name)
//        authentication.attributes.forEach { println(it) }
//        println("---")
//        authentication.roles.forEach { println(it) }
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