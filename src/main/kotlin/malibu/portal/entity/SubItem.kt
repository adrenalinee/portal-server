package malibu.portal.entity

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import malibu.portal.operate.dto.subitem.SubItemCreateSpec
import malibu.portal.operate.dto.subitem.SubItemDto

@Entity
@Serdeable
open class SubItem(
    name: String,

    url: String,

    parent: Item? = null,

    description: String? = null,
): BaseEntity() {
    var name: String = name
        protected set

    var url: String = url
        protected set

    @JoinColumn(name = "parent_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    var parent: Item? = parent
        protected set

    var description: String? = description
        protected set

    companion object {
        fun create(parent: Item, createSpec: SubItemCreateSpec): SubItem {
            return SubItem(
                name = createSpec.name,
                url = createSpec.url,
                description = createSpec.description,
                parent = parent,
            )
        }
    }

    fun toDto(): SubItemDto {
        return SubItemDto(
            name = name,
            url = url,
            description = description,
        )
    }
}