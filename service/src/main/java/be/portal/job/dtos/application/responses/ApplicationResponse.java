package be.portal.job.dtos.application.responses;

import be.portal.job.entities.Application;
import be.portal.job.enums.ApplicationStatus;

import java.time.LocalDateTime;

public record ApplicationResponse(

        LocalDateTime apply_date,
        ApplicationStatus applicationStatus
) {
    public Application toEntity() {
        Application application = new Application();

        application.setApplyDate(apply_date);
        application.setApplicationStatus(applicationStatus);

        return application;
    }
}
