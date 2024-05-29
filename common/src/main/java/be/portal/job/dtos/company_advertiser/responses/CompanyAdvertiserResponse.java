package be.portal.job.dtos.company_advertiser.responses;

import be.portal.job.dtos.company.responses.CompanyResponse;
import be.portal.job.dtos.user.responses.JobAdvertiserShortResponse;

public record CompanyAdvertiserResponse(
        JobAdvertiserShortResponse jobAdvertiser,
        CompanyResponse company
) { }
