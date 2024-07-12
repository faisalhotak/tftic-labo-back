package be.portal.job.dtos.company_advertiser.responses;

import be.portal.job.dtos.company.responses.CompanyResponse;
import be.portal.job.dtos.user.responses.UserShortResponse;

public record CompanyAdvertiserResponse(
        Long id,
        UserShortResponse jobAdvertiser,
        CompanyResponse company
) { }
