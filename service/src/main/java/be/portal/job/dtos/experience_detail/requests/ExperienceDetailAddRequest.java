package be.portal.job.dtos.experience_detail.requests;

import be.portal.job.entities.ExperienceDetail;
import be.portal.job.entities.JobSeeker;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ExperienceDetailAddRequest(

        @NotBlank(message = "Company name cannot be blank")
        String companyName,

        @NotBlank(message = "Description cannot be blank")
        String description,

        @NotNull(message = "Start date cannot be null")
        LocalDateTime startDate,

        @Nullable
        LocalDateTime endDate

) {
    public ExperienceDetail toEntity(JobSeeker jobSeeker) {
        return new ExperienceDetail(
                companyName,
                description,
                startDate,
                endDate,
                jobSeeker
        );
    }
}
