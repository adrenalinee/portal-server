package malibu.portal.entity

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import malibu.portal.operate.dto.subitem.ItemLinkCreateSpec
import malibu.portal.operate.dto.subitem.ItemLinkDto

@Entity
@Serdeable
open class ItemLink(
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
        fun create(parent: Item, createSpec: ItemLinkCreateSpec): ItemLink {
            return ItemLink(
                name = createSpec.name,
                url = createSpec.url,
                description = createSpec.description,
                parent = parent,
            )
        }
    }

    fun toDto(): ItemLinkDto {
        return ItemLinkDto(
            name = name,
            url = url,
            description = description,
        )
    }
}