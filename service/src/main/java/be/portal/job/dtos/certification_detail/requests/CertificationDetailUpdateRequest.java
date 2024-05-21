package be.portal.job.dtos.certification_detail.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CertificationDetailUpdateRequest(

        @NotBlank(message = "Certification name cannot be blank")
        String name,

        @NotBlank(message = "Description cannot be blank")
        String description,

        @NotNull(message = "Completion date cannot be blank")
        LocalDateTime completionDate
) {
}
