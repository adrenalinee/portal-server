package malibu.portal.server.tag

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
import malibu.portal.operate.DomainOperation
import malibu.portal.operate.dto.tag.TagCreateSpec
import malibu.portal.operate.dto.tag.TagDto
import malibu.portal.operate.dto.tag.TagSearchSpec
import malibu.portal.operate.dto.tag.TagUpdateSpec
import java.util.UUID

@Validated
@Controller("/tags")
class TagController(
    private val tagService: TagService
): DomainOperation<UUID, TagDto, TagDto, TagSearchSpec, TagCreateSpec, TagUpdateSpec> {

    @Post
    override fun create(
        @Valid @Body createSpec: TagCreateSpec
    ): TagDto {
        return tagService.create(createSpec)
    }

    @Get("{?searchSpec*}")
    override fun search(
        @Valid searchSpec: TagSearchSpec,
        pageable: Pageable
    ): Page<TagDto> {
        return tagService.search(searchSpec, pageable)
    }

    @Get("/{tagId}")
    override fun getOne(tagId: UUID): TagDto? {
        return tagService.getOne(tagId)
    }

    @Patch("/{tagId}")
    override fun update(
        tagId: UUID,
        @Valid @Body updateSpec: TagUpdateSpec
    ): TagDto {
        return tagService.update(tagId, updateSpec)
    }

    @Delete("/{tagId}")
    override fun delete(tagId: UUID) {
        return tagService.delete(tagId)
    }
}