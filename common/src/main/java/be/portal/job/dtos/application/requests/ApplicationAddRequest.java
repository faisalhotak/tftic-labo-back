package be.portal.job.dtos.application.requests;

import be.portal.job.entities.Application;
import be.portal.job.entities.JobOffer;
import be.portal.job.entities.JobSeeker;
import be.portal.job.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ApplicationAddRequest(

        @NotNull(message = "Apply date cannot be null")
        LocalDateTime applyDate,

        @NotNull(message = "Application status cannot be null")
        ApplicationStatus applicationStatus,

        @NotNull(message = "Job offer ID cannot be null")
        Long jobOfferId
) {
    public Application toEntity(JobSeeker jobSeeker, JobOffer jobOffer) {
        return new Application(
                applyDate,
                applicationStatus,
                jobSeeker,
                jobOffer
        );
    }
}
