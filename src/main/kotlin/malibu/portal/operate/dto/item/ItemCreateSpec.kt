package malibu.portal.operate.dto.item

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import malibu.portal.operate.dto.item.extralink.ItemExtraLinkCreateSpec

@Serdeable
@Introspected
data class ItemCreateSpec(
    @field:NotBlank
    @field:Size(min = 2, max = 50)
    val name: String?,

    @field:NotBlank
    @field:Size(min = 2, max = 500)
    val representLink: String?,

    @field:Size(min = 2, max = 500)
    val faviconLink: String? = null,

    val description: String? = null,

    val extraLinks: List<ItemExtraLinkCreateSpec>? = null,
)
