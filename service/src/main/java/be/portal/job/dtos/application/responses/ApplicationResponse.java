package be.portal.job.dtos.application.responses;

import be.portal.job.entities.Application;
import be.portal.job.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ApplicationResponse(

        @NotNull(message = "Apply date cannot be null")
        LocalDateTime applyDate,

        @NotNull(message = "Application status cannot be null")
        ApplicationStatus applicationStatus
) {
    public static ApplicationResponse fromEntity(Application application) {
        return new ApplicationResponse(
                application.getApplyDate(),
                application.getApplicationStatus()
        );
    }
}
