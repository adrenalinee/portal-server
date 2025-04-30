package malibu.portal.operate.dto.tag

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
data class TagUpdateSpec(
    val name: String? = null,
    val description: String? = null
)