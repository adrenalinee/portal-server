package malibu.portal.operate.dto.tag

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
data class TagSearchSpec(
    val tagId: String? = null,
    val name: String? = null,
)
