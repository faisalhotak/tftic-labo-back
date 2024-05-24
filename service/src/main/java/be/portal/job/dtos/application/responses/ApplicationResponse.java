package be.portal.job.dtos.application.responses;

import be.portal.job.dtos.job_offer.responses.JobOfferResponse;
import be.portal.job.dtos.user.responses.JobSeekerResponse;
import be.portal.job.entities.Application;
import be.portal.job.enums.ApplicationStatus;

import java.time.LocalDateTime;

public  record ApplicationResponse(
        Long id,
        LocalDateTime applyDate,
        ApplicationStatus applicationStatus,
        JobOfferResponse jobOffer,
        JobSeekerResponse jobSeeker
) {
    public static ApplicationResponse fromEntity(Application application) {
        return new ApplicationResponse(
                application.getId(),
                application.getApplyDate(),
                application.getApplicationStatus(),
                JobOfferResponse.fromEntity(application.getJobOffer()),
                JobSeekerResponse.fromEntity(application.getJobSeeker())
        );
    }
}
