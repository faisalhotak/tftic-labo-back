package be.portal.job.dtos.experience_detail.requests;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ExperienceDetailUpdateRequest(

        @NotBlank(message = "Company name cannot be blank")
        String companyName,

        @NotBlank(message = "Description cannot be blank")
        @Size(min = 1, max = 500, message = "Description must be between 1 and 500 characters")
        String description,

        @NotNull(message = "Start date cannot be null")
        LocalDateTime startDate,

        @Nullable
        LocalDateTime endDate
) { }
