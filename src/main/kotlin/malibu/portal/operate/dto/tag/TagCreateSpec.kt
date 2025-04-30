package malibu.portal.operate.dto.tag

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
data class TagCreateSpec(
    val name: String,
    val description: String? = null
)
