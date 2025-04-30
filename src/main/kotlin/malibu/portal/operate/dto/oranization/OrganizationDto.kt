package malibu.portal.operate.dto.oranization

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDateTime

@Serdeable
@Introspected
data class OrganizationDto(
    val organizationId: String,
    val name: String,
    val description: String? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
