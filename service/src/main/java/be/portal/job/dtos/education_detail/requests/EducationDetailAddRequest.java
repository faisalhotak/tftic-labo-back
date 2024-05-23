package be.portal.job.dtos.education_detail.requests;

import be.portal.job.entities.EducationDetail;
import be.portal.job.entities.JobSeeker;
import be.portal.job.enums.DegreeType;
import be.portal.job.enums.Mention;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EducationDetailAddRequest(

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
    public EducationDetail toEntity(JobSeeker jobSeeker) {
        return new EducationDetail(
                instituteName,
                major,
                degreeType,
                mention,
                startDate,
                completionDate,
                jobSeeker
        );
    }
}
