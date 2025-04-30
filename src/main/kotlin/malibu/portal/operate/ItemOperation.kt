package malibu.portal.operate

import malibu.portal.operate.dto.item.ItemCreateSpec
import malibu.portal.operate.dto.item.ItemDto
import malibu.portal.operate.dto.item.ItemSearchSpec
import malibu.portal.operate.dto.item.ItemUpdateSpec

interface ItemOperation: BaseOperation<
    String,
    ItemDto,
    ItemSearchSpec,
    ItemCreateSpec,
    ItemUpdateSpec,
>