package malibu.portal.operate.dto.item

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import malibu.portal.operate.dto.subitem.SubItemCreateSpec

@Serdeable
@Introspected
data class ItemUpdateSpec(
    val name: String? = null,
    val url: String? = null,
    val description: String? = null,
    val children: List<SubItemCreateSpec>? = null
)
