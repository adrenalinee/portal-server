package malibu.portal.operate.dto.item

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
@Introspected
data class ItemSearchSpec(
    val itemId: String? = null,
    val name: String? = null,
    val url: String? = null,
    val tagNames: List<String>? = null,
    val tagIds: List<UUID>? = null,
)
