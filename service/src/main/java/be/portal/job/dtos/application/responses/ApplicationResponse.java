package be.portal.job.dtos.application.responses;

import be.portal.job.entities.Application;
import be.portal.job.entities.JobOffer;
import be.portal.job.entities.JobSeeker;
import be.portal.job.enums.ApplicationStatus;

import java.time.LocalDateTime;

public  record ApplicationResponse(
        Long id,
        LocalDateTime apply_date,
        ApplicationStatus applicationStatus,
        JobOffer jobOffer,
        JobSeeker jobSeeker
) {
    public static ApplicationResponse fromEntity(Application application) {
        return new ApplicationResponse(

                application.getId(),
                application.getApplyDate(),
                application.getApplicationStatus(),
                application.getJobOffer(),
                application.getJobSeeker()
        );
    }
}
