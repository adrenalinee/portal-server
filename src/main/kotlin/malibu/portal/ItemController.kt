package malibu.portal

import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import jakarta.validation.Valid
import malibu.portal.operate.dto.item.ItemCreateSpec
import malibu.portal.operate.dto.item.ItemDto
import malibu.portal.operate.dto.item.ItemSearchSpec

@Validated
@Controller("/items")
open class ItemController(
    private val itemService: ItemService
) {
    @Post
    open fun create(
        @Valid @Body createSpec: ItemCreateSpec
    ): ItemDto {
        return itemService.create(createSpec)
    }

    @Get("{?searchSpec*}")
    open fun findAll(
        @Valid searchSpec: ItemSearchSpec,
        pageable: Pageable
    ): Page<ItemDto> {
        return itemService.search(searchSpec, pageable)
    }

//    @Patch("{id}")
//    open fun patch (
//        id: UUID,
//        @Valid @Body updateSpec: ItemUpdateSpec
//    ): ItemDto {
//        return itemService.patch(id, updateSpec).toDto()
//    }
}

//@Singleton
//open class ItemService(
//    private val itemRepo: ItemRepo
//) {
//    @Transactional
//    open fun create(
//        @Valid @Body createSpec: ItemCreateSpec
//    ): Item {
//        return itemRepo.save(Item.create(
//            organizationId = "1234",
//            createSpec = createSpec
//        ))
//    }
//
//    @Transactional(readOnly = true)
//    open fun findAll(
//        searchSpec: ItemSearchSpec?,
//        pageable: Pageable
//    ): Page<Item> {
//        return itemRepo.findAll(pageable)
//            .also { page ->
//                page.content.forEach { item ->
//                    println(item.children)
//                    println(item.tags)
//                }
//            }
//    }
//
//    @Transactional
//    open fun patch(
//        id: UUID,
//        updateSpec: ItemUpdateSpec
//    ): Item {
//        val item = itemRepo.findById(id)
//            .orElseThrow { RuntimeException("item not found. id: $id") }
//
//        item.patch(updateSpec)
//
//        return item
//    }
//}