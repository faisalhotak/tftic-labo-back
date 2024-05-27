package be.portal.job.dtos.education_detail.responses;

import be.portal.job.entities.EducationDetail;
import be.portal.job.enums.DegreeType;
import be.portal.job.enums.Mention;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EducationDetailResponse(

        @NotBlank(message = "Institute name cannot be blank")
        String instituteName,

        @NotBlank(message = "Major cannot be blank")
        String major,

        @NotNull(message = "Degree type cannot be null")
        DegreeType degreeType,

        @NotNull(message = "Mention cannot be null")
        Mention mention,

        @NotNull(message = "Start date cannot be null")
        LocalDate startDate,

        @NotNull(message = "Completion date cannot be null")
        LocalDate completionDate
) {
    public static EducationDetailResponse fromEntity(EducationDetail educationDetail) {
        return new EducationDetailResponse(
                educationDetail.getInstituteName(),
                educationDetail.getMajor(),
                educationDetail.getDegreeType(),
                educationDetail.getMention(),
                educationDetail.getStartDate(),
                educationDetail.getCompletionDate()
        );
    }
}