package malibu.portal.server.exception

import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import java.util.*

class ItemNotFoundException(
    itemId: UUID
): HttpStatusException(
    HttpStatus.BAD_REQUEST,
    "item 을 찾을 수 없습니다. itemId: $itemId"
)