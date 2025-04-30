package malibu.portal.entity

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import malibu.portal.operate.dto.item.ItemTagDto

@Entity
@Serdeable
class ItemTag(
    @Id
    @GeneratedValue
    val id: Long = 0,

    @JoinColumn(name = "link_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    var item: Item,

    @JoinColumn(name = "tag_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    var tag: Tag,
): BaseEntity() {

    companion object {
        fun create(item: Item, tag: Tag): ItemTag {
            return ItemTag(
                item = item,
                tag = tag
            )
        }
    }

    fun toDto(): ItemTagDto {
        return ItemTagDto(
            linkItemTagId = id,
            tag = tag.toDto(),
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }
}
