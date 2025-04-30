package malibu.portal.operate.dto.item

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import malibu.portal.operate.dto.tag.TagDto
import java.time.LocalDateTime

@Serdeable
@Introspected
data class ItemTagDto(
    val linkItemTagId: Long,
    val tag: TagDto,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)