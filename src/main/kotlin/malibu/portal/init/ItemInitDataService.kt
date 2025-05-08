package malibu.portal.init

import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import malibu.portal.operate.dto.item.ItemCreateSpec
import malibu.portal.operate.dto.item.ItemDto
import malibu.portal.operate.dto.item.ItemTagCreateSpec
import malibu.portal.operate.dto.item.extralink.ItemExtraLinkCreateSpec
import malibu.portal.operate.dto.tag.TagDto
import malibu.portal.server.item.ItemService
import malibu.portal.server.item.ItemTagService

@Singleton
class ItemInitDataService(
    private val itemService: ItemService,
    private val itemTagService: ItemTagService
) {
    @Transactional
    fun init(tags: List<TagDto>): List<ItemDto> {
        val results = mutableListOf<ItemDto>()
        createItem(
            tag = tags.first { tag -> tag.name == "미국" },
            createSpec = ItemCreateSpec(
                name = "google",
                representLink = "https://google.com",
                faviconLink = "https://www.google.com/images/branding/googleg/1x/googleg_standard_color_128dp.png",
                description = "구글입니다.",
                extraLinks = listOf(
                    ItemExtraLinkCreateSpec(
                        name = "google gemini",
                        url = "https://gemini.google.com",
                        description = "구글 제미니입니다."
                    )
                )
            )
        ).also { results.add(it) }
        createItem(
            tag = tags.first { tag -> tag.name == "한국" },
            createSpec = ItemCreateSpec(
                name = "naver",
                representLink = "https://naver.com",
                description = "네이버입니다."
            )
        ).also { results.add(it) }

        return results
    }


    private fun createItem(tag: TagDto, createSpec: ItemCreateSpec): ItemDto {
        val item = itemService.create(createSpec)
        itemTagService.create(item.itemId, ItemTagCreateSpec(
            tagId = tag.tagId
        ))

        return item
    }
}