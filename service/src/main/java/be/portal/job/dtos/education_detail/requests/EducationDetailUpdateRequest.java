package be.portal.job.dtos.education_detail.requests;

import be.portal.job.enums.DegreeType;
import be.portal.job.enums.Mention;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EducationDetailUpdateRequest(

        @NotBlank(message = "Institute name cannot be blank")
        String instituteName,

        @NotBlank(message = "Major cannot be blank")
        String major,

        @NotNull(message = "Degree type cannot be null")
        DegreeType degreeType,

        @NotNull(message = "Mention cannot be null")
        Mention mention,

        @NotNull(message = "Start date cannot be null")
        LocalDateTime startDate,

        @NotNull(message = "Completion date cannot be null")
        LocalDateTime completionDate
) { }
