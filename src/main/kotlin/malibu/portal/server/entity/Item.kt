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
    representLink: String,
    faviconLink: String? = null,
    description: String? = null,
): BaseEntity() {

    var organizationId: String = organizationId
        protected set

    @Column(nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    var representLink: String = representLink
        protected set

    @OneToMany(mappedBy = "parent", cascade = [CascadeType.ALL])
    protected val mutableLinks: MutableList<ItemExtraLink> = mutableListOf()
    val extraLinks: List<ItemExtraLink>
        get() = mutableLinks.toList()

    @OneToMany(mappedBy = "item", cascade = [CascadeType.ALL])
    protected val mutableTags: MutableList<ItemTag> = mutableListOf()
    val tags: List<ItemTag>
        get() = mutableTags.toList()

    var faviconLink: String? = faviconLink
        protected set

    var description: String? = description
        protected set

    companion object {
        fun create(organizationId: String, createSpec: ItemCreateSpec): Item {
            return Item(
                organizationId = organizationId,
                name = createSpec.name!!,
                representLink = createSpec.representLink!!,
                faviconLink = createSpec.faviconLink,
                description = createSpec.description,
            ).also { item ->
                item.mutableLinks.addAll(
                    createSpec.extraLinks?.map { subItemCreateSpec ->
                        ItemExtraLink.create(item, subItemCreateSpec)
                    } ?: listOf()
                )
            }
        }
    }

    fun update(updateSpec: ItemUpdateSpec) {
        updateSpec.name?.also { name = it }
        updateSpec.representLink?.also { representLink = it }
        updateSpec.faviconLink?.also { faviconLink = it }
        updateSpec.description?.also { description = it }
        updateSpec.links?.also {
            mutableLinks.clear()
            mutableLinks.addAll(it.map { children ->
                ItemExtraLink.create(this, children)
            })
        }
    }

    fun toDto(): ItemDto {
        return ItemDto(
            itemId = getId(),
            organizationId = organizationId,
            name = name,
            representLink = representLink,
            faviconLink = faviconLink,
            description = description,
            extraLinks = extraLinks.map { subItem -> subItem.toDto() },
            itemTags = tags.map { linkTag -> linkTag.toDto() },
            version = version,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    fun toDtoSimple(): ItemDtoSimple {
        return ItemDtoSimple(
            itemId = getId(),
            organizationId = organizationId,
            name = name,
            representLink = representLink,
            faviconLink = faviconLink,
            description = description,
//            extraLinks = extraLinks.map { subItem -> subItem.toDto() },
            version = version,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }
}