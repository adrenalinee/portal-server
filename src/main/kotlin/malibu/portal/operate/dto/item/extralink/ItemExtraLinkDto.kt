package malibu.portal.operate.dto.item.extralink

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
data class ItemExtraLinkDto(
    val name: String,
    val url: String,
    val description: String? = null,
)
