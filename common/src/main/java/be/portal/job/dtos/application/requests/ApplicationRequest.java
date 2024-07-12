package be.portal.job.dtos.application.requests;

import jakarta.validation.constraints.NotNull;

public record ApplicationRequest(

        @NotNull(message = "The job offer id is required")
        Long jobOfferId
) { }
