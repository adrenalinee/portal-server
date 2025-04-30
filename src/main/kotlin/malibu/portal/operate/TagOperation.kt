package malibu.portal.operate

import malibu.portal.operate.dto.tag.TagCreateSpec
import malibu.portal.operate.dto.tag.TagDto
import malibu.portal.operate.dto.tag.TagSearchSpec
import malibu.portal.operate.dto.tag.TagUpdateSpec

interface TagOperation: BaseOperation<
        String,
        TagDto,
        TagSearchSpec,
        TagCreateSpec,
        TagUpdateSpec,
        >