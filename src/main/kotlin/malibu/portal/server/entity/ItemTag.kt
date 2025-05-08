package malibu.portal.server.entity

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import malibu.portal.operate.dto.item.ItemTagDto
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Serdeable
class ItemTag(
    item: Item,
    tag: Tag,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

    @JoinColumn(name = "link_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    var item: Item = item
        protected set

    @JoinColumn(name = "tag_id")
    @ManyToOne(optional = false) //, fetch = FetchType.LAZY)
    var tag: Tag = tag
        protected set

    @CreationTimestamp
    var createdAt: LocalDateTime = LocalDateTime.MIN
        protected set

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
            tag = tag.toDto(),
            createdAt = createdAt,
        )
    }
}
