package malibu.portal.server.entity

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Entity
import malibu.portal.operate.dto.tag.TagCreateSpec
import malibu.portal.operate.dto.tag.TagDto
import malibu.portal.operate.dto.tag.TagUpdateSpec

@Entity
@Serdeable
open class Tag(
    name: String,
    organizationId: String,
    description: String? = null,
): BaseEntity() {

    var name: String = name
        protected set

    var organizationId: String = organizationId
        protected set

    var description: String? = description
        protected set

    companion object {
        fun create(organizationId: String, createSpec: TagCreateSpec): Tag {
            return Tag(
                organizationId = organizationId,
                name = createSpec.name,
                description = createSpec.description,
            )
        }
    }

    fun update(updateSpec: TagUpdateSpec) {
        updateSpec.name?.also { name = it }
        updateSpec.description?.also { description = it }
    }

    fun toDto(): TagDto {
        return TagDto(
            tagId = getId(),
            organizationId = organizationId,
            name = name,
            description = description,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }
}