package be.portal.job.dtos.certification_detail.responses;

import be.portal.job.entities.CertificationDetail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CertificationDetailResponse(

        @NotBlank(message = "Certification name cannot be blank")
        String name,

        @NotBlank(message = "Description cannot be blank")
        String description,

        @NotNull(message = "Completion date cannot be blank")
        LocalDate completionDate
) {
    public static CertificationDetailResponse fromEntity(CertificationDetail certificationDetail) {
        return new CertificationDetailResponse(
                certificationDetail.getName(),
                certificationDetail.getDescription(),
                certificationDetail.getCompletionDate()
        );
    }
}
