package be.portal.job.dtos.experience_detail.requests;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ExperienceDetailRequest(

        @NotBlank(message = "Company name cannot be blank")
        String companyName,

        @NotBlank(message = "Description cannot be blank")
        String description,

        @NotNull(message = "Start date cannot be null")
        LocalDate startDate,

        @Nullable
        LocalDate endDate

) { }
