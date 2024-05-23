package be.portal.job.dtos.experience_detail.responses;

import be.portal.job.entities.ExperienceDetail;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ExperienceDetailResponse(

        @NotBlank(message = "Company name cannot be blank")
        String companyName,

        @NotBlank(message = "Description cannot be blank")
        String description,

        @NotNull(message = "Start date cannot be null")
        LocalDate startDate,

        @Nullable
        LocalDate endDate
) {
    public static ExperienceDetailResponse fromEntity(ExperienceDetail experienceDetail) {
        return new ExperienceDetailResponse(
                experienceDetail.getCompanyName(),
                experienceDetail.getDescription(),
                experienceDetail.getStartDate(),
                experienceDetail.getEndDate()
        );
    }
}
