package malibu.portal.operate.dto.item

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import malibu.portal.operate.dto.subitem.ItemLinkDto
import java.time.LocalDateTime
import java.util.*

@Serdeable
@Introspected
data class ItemDtoSimple(
    val linkItemId: UUID,
    val organizationId: String,
    val name: String,
    val description: String? = null,
    val links: List<ItemLinkDto>,
    val version: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
