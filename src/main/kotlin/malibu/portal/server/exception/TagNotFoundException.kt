package malibu.portal.server.exception

import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import java.util.UUID

class TagNotFoundException(
    tagId: UUID
): HttpStatusException(
    HttpStatus.BAD_REQUEST,
    "tag 를 찾을 수 없습니다. tagId: $tagId"
)