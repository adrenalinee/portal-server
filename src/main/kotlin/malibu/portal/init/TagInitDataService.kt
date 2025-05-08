package malibu.portal.init

import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import malibu.portal.operate.dto.tag.TagCreateSpec
import malibu.portal.operate.dto.tag.TagDto
import malibu.portal.server.tag.TagService

@Singleton
class TagInitDataService(
    private val tagService: TagService
) {

    @Transactional
    fun init(): List<TagDto> {
        val results = mutableListOf<TagDto>()
        tagService.create(TagCreateSpec(
            name = "미국",
            description = "미국 태그입니다."
        )).also { results.add(it) }
        tagService.create(TagCreateSpec(
            name = "한국",
            description = "한국 태그입니다."
        )).also { results.add(it) }
        return results
    }
}