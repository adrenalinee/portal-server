package malibu.portal.operate.dto.subitem

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
data class ItemLinkCreateSpec(
    val name: String,
    val url: String,
    val description: String? = null
)
