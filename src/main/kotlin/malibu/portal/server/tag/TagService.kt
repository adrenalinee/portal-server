package malibu.portal.server.tag

import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import malibu.portal.operate.DomainOperation
import malibu.portal.operate.dto.tag.TagCreateSpec
import malibu.portal.operate.dto.tag.TagDto
import malibu.portal.operate.dto.tag.TagSearchSpec
import malibu.portal.operate.dto.tag.TagUpdateSpec
import malibu.portal.server.entity.Tag
import malibu.portal.server.exception.TagNotFoundException
import java.util.*

@Singleton
class TagService(
    private val tagRepo: TagRepo
): DomainOperation<UUID, TagDto, TagDto, TagSearchSpec, TagCreateSpec, TagUpdateSpec> {

    @Transactional
    override fun create(createSpec: TagCreateSpec): TagDto {
        return tagRepo.saveAndFlush(
            Tag.create(
                organizationId = "1234",
                createSpec = createSpec
            )
        ).toDto()
    }

    @Transactional(readOnly = true)
    override fun search(
        searchSpec: TagSearchSpec,
        pageable: Pageable
    ): Page<TagDto> {
        return tagRepo.findAll(pageable).map { it.toDto() }
    }

    @Transactional(readOnly = true)
    override fun getOne(id: UUID): TagDto? {
        return tagRepo.findById(id)
            .map { it.toDto() }
            .orElse(null)
    }

    @Transactional
    override fun update(
        id: UUID,
        updateSpec: TagUpdateSpec
    ): TagDto {
        val tag = tagRepo.findById(id)
            .orElseThrow { TagNotFoundException(id) }

        tag.update(updateSpec)

        return tagRepo.saveAndFlush(tag).toDto()
    }

    @Transactional
    override fun delete(id: UUID) {
        val tag = tagRepo.findById(id)
            .orElseThrow { TagNotFoundException(id) }

        tagRepo.delete(tag)
    }
}