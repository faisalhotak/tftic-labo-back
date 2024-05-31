package be.portal.job.dtos.company.requests;

import jakarta.validation.constraints.NotNull;

public record CompanyIdRequest(
        @NotNull(message = "Company id is required")
        Long id
) { }
