package be.portal.job.dtos.application.requests;

import be.portal.job.entities.Application;
import be.portal.job.entities.JobOffer;
import be.portal.job.entities.JobSeeker;
import be.portal.job.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ApplicationRequest(

        @NotNull(message = "The date is required")
        LocalDateTime apply_date,

        @NotNull(message = "The status is required")
        ApplicationStatus applicationStatus,

        @NotNull(message = "The id is required")
        Long jobOfferId

) {
    public Application toEntity(JobSeeker jobSeeker, JobOffer jobOffer) {

        Application application = new Application();

        application.setApplyDate(apply_date);
        application.setApplicationStatus(applicationStatus);
        application.setJobOffer(jobOffer);
        application.setJobSeeker(jobSeeker);

        return application;

    }
}
