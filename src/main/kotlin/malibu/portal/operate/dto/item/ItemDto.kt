package malibu.portal.operate.dto.item

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import malibu.portal.operate.dto.subitem.SubItemDto
import java.time.LocalDateTime
import java.util.*

@Serdeable
@Introspected
data class ItemDto(
    val linkItemId: UUID,
    val organizationId: String,
    val name: String,
    val url: String,
    val description: String? = null,
    val children: List<SubItemDto>,
    val tags: List<ItemTagDto>,
    val version: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
