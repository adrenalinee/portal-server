package malibu.portal.operate.dto.oranization

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
data class OrganizationSearchSpec(
    val organizationId: String? = null,
    val name: String? = null
)
