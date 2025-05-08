package malibu.portal.operate.dto.item

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDateTime
import java.util.*

@Serdeable
@Introspected
data class ItemDtoSimple(
    val itemId: UUID,
    val organizationId: String,
    val name: String,
    val representLink: String,
    val faviconLink: String? = null,
    val description: String? = null,
//    val extraLinks: List<ItemExtraLinkDto>,
    val version: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
