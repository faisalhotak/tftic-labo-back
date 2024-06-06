package be.portal.job.dtos.application.requests;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ApplicationRequest(

        @NotNull(message = "The date is required")
        LocalDateTime applyDate,

        @NotNull(message = "The job offer id is required")
        Long jobOfferId
) { }
