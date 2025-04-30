package malibu.portal.operate

import malibu.portal.operate.dto.oranization.OrganizationCreateSpec
import malibu.portal.operate.dto.oranization.OrganizationDto
import malibu.portal.operate.dto.oranization.OrganizationSearchSpec
import malibu.portal.operate.dto.oranization.OrganizationUpdateSpec

interface OrganizationOperation: BaseOperation<
    String,
    OrganizationDto,
    OrganizationSearchSpec,
    OrganizationCreateSpec,
    OrganizationUpdateSpec
>