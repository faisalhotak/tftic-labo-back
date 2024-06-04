package be.portal.job.dtos.company_advertiser.requests;

import be.portal.job.enums.AdvertiserRole;
import jakarta.validation.constraints.NotNull;

public record CompanyAdvertiserUpdateRequest (
        @NotNull(message = "The advertiser role is required")
        AdvertiserRole advertiserRole
) { }
