package malibu.portal.operate.dto.item

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
@Introspected
data class ItemTagCreateSpec(
    val tagId: UUID,
)
