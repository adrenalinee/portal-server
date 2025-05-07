package malibu.portal.server.entity

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import malibu.portal.operate.dto.item.ItemCreateSpec
import malibu.portal.operate.dto.item.ItemDto
import malibu.portal.operate.dto.item.ItemDtoSimple
import malibu.portal.operate.dto.item.ItemUpdateSpec

@Entity
@Serdeable
class Item(
    organizationId: String,
    name: String,
    description: String? = null,
): BaseEntity() {

    var organizationId: String = organizationId
        protected set

    @Column(nullable = false)
    var name: String = name
        protected set

    @OneToMany(mappedBy = "parent", cascade = [CascadeType.ALL])
    protected val mutableLinks: MutableList<ItemLink> = mutableListOf()
    val links: List<ItemLink>
        get() = mutableLinks.toList()

    @OneToMany(mappedBy = "item", cascade = [CascadeType.ALL])
    protected val mutableTags: MutableSet<ItemTag> = mutableSetOf()
    val tags: Set<ItemTag>
        get() = mutableTags.toSet()

    var description: String? = description
        protected set

    companion object {
        fun create(organizationId: String, createSpec: ItemCreateSpec): Item {
            return Item(
                organizationId = organizationId,
                name = createSpec.name,
                description = createSpec.description,
            ).also { item ->
                item.mutableLinks.addAll(
                    createSpec.links?.map { subItemCreateSpec ->
                        ItemLink.create(item, subItemCreateSpec)
                    } ?: listOf()
                )
            }
        }
    }

    fun update(updateSpec: ItemUpdateSpec) {
        updateSpec.name?.also { name = it }
        updateSpec.description?.also { description = it }
        updateSpec.links?.also {
            mutableLinks.clear()
            mutableLinks.addAll(it.map { children ->
                ItemLink.create(this, children)
            })
        }
    }

    fun toDto(): ItemDto {
        return ItemDto(
            linkItemId = getId(),
            organizationId = organizationId,
            name = name,
            description = description,
            links = links.map { subItem -> subItem.toDto() },
//            tags = tags.map { linkTag -> linkTag.toDto() },
            version = version,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    fun toDtoSimple(): ItemDtoSimple {
        return ItemDtoSimple(
            linkItemId = getId(),
            organizationId = organizationId,
            name = name,
            description = description,
            links = links.map { subItem -> subItem.toDto() },
            version = version,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }
}