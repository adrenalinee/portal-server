package malibu.portal.entity

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Entity
import jakarta.persistence.Id
import malibu.portal.operate.dto.tag.TagDto
import malibu.portal.entity.BaseEntity

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