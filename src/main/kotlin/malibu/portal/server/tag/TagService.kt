package malibu.portal.server.tag

import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import malibu.portal.entity.Tag
import malibu.portal.operate.ServiceOperation
import malibu.portal.operate.dto.tag.TagCreateSpec
import malibu.portal.operate.dto.tag.TagDto
import malibu.portal.operate.dto.tag.TagSearchSpec
import malibu.portal.operate.dto.tag.TagUpdateSpec
import java.util.*

@Singleton
class TagService(
    private val tagRepo: TagRepo
): ServiceOperation<UUID, TagDto, TagDto, TagSearchSpec, TagCreateSpec, TagUpdateSpec> {

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
        TODO("Not yet implemented")
    }

    @Transactional
    override fun update(
        id: UUID,
        updateSpec: TagUpdateSpec
    ): TagDto {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun delete(id: UUID) {
        TODO("Not yet implemented")
    }


}