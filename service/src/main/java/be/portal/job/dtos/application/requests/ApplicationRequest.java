package be.portal.job.dtos.application.requests;

import be.portal.job.entities.Application;
import be.portal.job.enums.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record ApplicationRequest(

        @NotBlank(message = "The date is required")
        LocalDateTime apply_date,

        @NotBlank(message = "The status is required")
        ApplicationStatus applicationStatus
) {
    public Application toEntity() {
        Application application = new Application();

        application.setApplyDate(apply_date);
        application.setApplicationStatus(applicationStatus);

        return application;
    }
}
