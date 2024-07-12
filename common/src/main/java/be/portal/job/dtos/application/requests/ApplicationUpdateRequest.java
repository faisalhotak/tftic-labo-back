package be.portal.job.dtos.application.requests;

import be.portal.job.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

public record ApplicationUpdateRequest(

        @NotNull(message = "Application status cannot be null")
        ApplicationStatus applicationStatus
) { }
