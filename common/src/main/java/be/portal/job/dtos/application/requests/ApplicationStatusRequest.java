package be.portal.job.dtos.application.requests;

import be.portal.job.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

public record ApplicationStatusRequest(

        @NotNull(message = "Status is mandatory")
        ApplicationStatus status
) { }
