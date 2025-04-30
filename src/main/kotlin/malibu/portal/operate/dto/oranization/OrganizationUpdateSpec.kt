package malibu.portal.operate.dto.oranization

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
data class OrganizationUpdateSpec(
    val name: String? = null,
    val description: String? = null
)