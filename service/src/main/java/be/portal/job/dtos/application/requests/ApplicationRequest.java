package be.portal.job.dtos.application.requests;

import be.portal.job.entities.Application;
import be.portal.job.enums.ApplicationStatus;

import java.time.LocalDateTime;

public  record ApplicationRequest(
        Long id,
        LocalDateTime apply_date,
        ApplicationStatus applicationStatus
) {
    public static ApplicationRequest fromEntity(Application application) {
        return new ApplicationRequest(

                application.getId(),
                application.getApplyDate(),
                application.getApplicationStatus()
        );
    }
}
