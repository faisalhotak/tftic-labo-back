package be.portal.job.dtos.application.requests;

import be.portal.job.entities.Application;
import be.portal.job.entities.JobOffer;
import be.portal.job.entities.JobSeeker;
import be.portal.job.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ApplicationRequest(

        @NotNull(message = "The date is required")
        LocalDateTime applyDate,

        @NotNull(message = "The job offer id is required")
        Long jobOfferId
) {
    public Application toEntity(JobSeeker jobSeeker, JobOffer jobOffer, ApplicationStatus applicationStatus) {
        Application application = new Application();

        application.setApplyDate(applyDate);
        application.setJobOffer(jobOffer);
        application.setJobSeeker(jobSeeker);
        application.setApplicationStatus(applicationStatus);

        return application;
    }
}
