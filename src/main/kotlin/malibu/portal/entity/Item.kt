package malibu.portal.entity

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import malibu.portal.operate.dto.item.ItemCreateSpec
import malibu.portal.operate.dto.item.ItemDto
import malibu.portal.operate.dto.item.ItemUpdateSpec

@Entity
@Serdeable
class Item(
    organizationId: String,
    name: String,
    url: String,
    description: String? = null,
): BaseEntity() {

    companion object {
        fun create(organizationId: String, createSpec: ItemCreateSpec): Item {
            return Item(
                organizationId = organizationId,
                name = createSpec.name,
                url = createSpec.url,
                description = createSpec.description,
            ).also { item ->
                item.mutableChildren.addAll(
                    createSpec.children?.map { subItemCreateSpec ->
                        SubItem.create(item, subItemCreateSpec)
                    } ?: listOf()
                )
            }
        }
    }

    var organizationId: String = organizationId
        protected set

    @Column(nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    var url: String = url
        protected set

    @OneToMany(mappedBy = "parent", cascade = [CascadeType.ALL])
    val mutableChildren: MutableList<SubItem> = mutableListOf()
    val children: List<SubItem>
        get() = mutableChildren.toList()

    @OneToMany(mappedBy = "item")
    val mutableTags: MutableSet<ItemTag> = mutableSetOf()
    val tags: Set<ItemTag>
        get() = mutableTags.toSet()

    var description: String? = description
        protected set


    fun patch(updateSpec: ItemUpdateSpec) {
        updateSpec.name?.also { name = it }
        updateSpec.url?.also { url = it }
        updateSpec.description?.also { description = it }
        updateSpec.children?.also {
            mutableChildren.clear()
            mutableChildren.addAll(it.map { children ->
                SubItem.create(this, children)
            })
        }
    }

    fun toDto(): ItemDto {
        return ItemDto(
            linkItemId = getId(),
            organizationId = organizationId,
            name = name,
            url = url,
            description = description,
            children = children.map { subItem -> subItem.toDto() },
            tags = tags.map { linkTag -> linkTag.toDto() },
//            children = emptyList(),
//            tags = emptyList(),
            version = version,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }
}