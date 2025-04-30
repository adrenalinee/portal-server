package malibu.portal.operate.dto.oranization

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
data class OrganizationCreateSpec(
    val name: String,
    val description: String? = null
)
