package malibu.portal.server.tag

import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import jakarta.validation.Valid
import malibu.portal.operate.dto.tag.TagCreateSpec
import malibu.portal.operate.dto.tag.TagDto
import malibu.portal.operate.dto.tag.TagSearchSpec

@Controller("/tags")
open class TagController(
    private val tagService: TagService
) {

    @Post
    open fun create(
        @Valid @Body createSpec: TagCreateSpec
    ): TagDto {
        return tagService.create(createSpec)
    }

    @Get("{?searchSpec*}")
    open fun findAll(
        @Valid searchSpec: TagSearchSpec,
        pageable: Pageable
    ): Page<TagDto> {
        return tagService.search(searchSpec, pageable)
    }
}