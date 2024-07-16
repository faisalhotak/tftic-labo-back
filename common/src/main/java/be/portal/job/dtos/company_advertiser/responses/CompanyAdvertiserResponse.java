package be.portal.job.dtos.company_advertiser.responses;

import be.portal.job.dtos.company.responses.CompanyResponse;
import be.portal.job.dtos.user.responses.UserShortResponse;
import be.portal.job.enums.AdvertiserRole;

import java.time.LocalDateTime;

public record CompanyAdvertiserResponse(
        Long id,
        UserShortResponse jobAdvertiser,
        CompanyResponse company,
        AdvertiserRole advertiserRole,
        LocalDateTime createdAt
) { }
