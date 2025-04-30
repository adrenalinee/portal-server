package malibu.portal.operate.dto.item

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
data class ItemSearchSpec(
    val linkId: String? = null,
    val name: String? = null,
    val url: String? = null,
    val tags: List<String>? = null,
)
