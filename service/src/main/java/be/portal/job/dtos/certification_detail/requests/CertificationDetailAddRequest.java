package be.portal.job.dtos.certification_detail.requests;

import be.portal.job.entities.CertificationDetail;
import be.portal.job.entities.JobSeeker;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CertificationDetailAddRequest(

        @NotBlank(message = "Certification name cannot be blank")
        String name,

        @NotBlank(message = "Description cannot be blank")
        String description,

        @NotNull(message = "Completion date cannot be blank")
        LocalDateTime completionDate
) {
    public CertificationDetail toEntity(JobSeeker jobSeeker) {
        return new CertificationDetail(name, description, completionDate, jobSeeker);
    }
}
