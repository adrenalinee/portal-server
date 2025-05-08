package malibu.portal.operate.dto.item

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Size
import malibu.portal.operate.dto.item.extralink.ItemExtraLinkCreateSpec

@Serdeable
@Introspected
data class ItemUpdateSpec(

    @field:Size(min = 3, max = 50)
    val name: String? = null,

    @field:Size(min = 3, max = 500)
    val representLink: String? = null,

    @field:Size(min = 3, max = 500)
    val faviconLink: String? = null,

    val description: String? = null,
    val links: List<ItemExtraLinkCreateSpec>? = null,
)
