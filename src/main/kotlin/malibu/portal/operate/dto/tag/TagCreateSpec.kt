package malibu.portal.operate.dto.tag

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Serdeable
@Introspected
data class TagCreateSpec(
    @field:NotBlank
    @field:Size(min = 2, max = 50)
    val name: String,

    val description: String? = null
)
