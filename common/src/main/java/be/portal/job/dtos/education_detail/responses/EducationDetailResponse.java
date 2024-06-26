package be.portal.job.dtos.education_detail.responses;

import be.portal.job.enums.DegreeType;
import be.portal.job.enums.Mention;

import java.time.LocalDate;

public record EducationDetailResponse(
        Long id,
        String instituteName,
        String major,
        DegreeType degreeType,
        Mention mention,
        LocalDate startDate,
        LocalDate completionDate
) { }
