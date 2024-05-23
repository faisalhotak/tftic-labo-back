package be.portal.job.dtos.application.requests;

import be.portal.job.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ApplicationUpdateRequest(

        @NotNull(message = "Apply date cannot be null")
        LocalDateTime applyDate,

        @NotNull(message = "Application status cannot be null")
        ApplicationStatus applicationStatus
) {
}
