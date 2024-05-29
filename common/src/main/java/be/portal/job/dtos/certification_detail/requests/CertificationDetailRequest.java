package be.portal.job.dtos.certification_detail.requests;

import be.portal.job.entities.CertificationDetail;
import be.portal.job.entities.JobSeeker;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CertificationDetailRequest(

        @NotBlank(message = "Certification name cannot be blank")
        String name,

        @NotBlank(message = "Description cannot be blank")
        String description,

        @NotNull(message = "Completion date cannot be blank")
        LocalDate completionDate
) {
    public CertificationDetail toEntity(JobSeeker jobSeeker) {

        CertificationDetail certificationDetail = new CertificationDetail();

        certificationDetail.setName(name);
        certificationDetail.setDescription(description);
        certificationDetail.setCompletionDate(completionDate);
        certificationDetail.setJobSeeker(jobSeeker);

        return certificationDetail;
    }
}
