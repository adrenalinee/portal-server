package malibu.portal.operate.dto.item

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import malibu.portal.operate.dto.subitem.ItemLinkCreateSpec

@Serdeable
@Introspected
data class ItemCreateSpec(
    val name: String,
    val description: String? = null,
    val links: List<ItemLinkCreateSpec>? = null,
)
