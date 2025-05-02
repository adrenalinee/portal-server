package malibu.portal.operate.dto.item

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import malibu.portal.operate.dto.subitem.ItemLinkCreateSpec

@Serdeable
@Introspected
data class ItemUpdateSpec(
    val name: String? = null,
    val description: String? = null,
    val links: List<ItemLinkCreateSpec>? = null,
)
