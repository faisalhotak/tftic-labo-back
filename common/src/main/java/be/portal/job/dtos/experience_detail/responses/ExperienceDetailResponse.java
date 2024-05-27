package be.portal.job.dtos.experience_detail.responses;

import be.portal.job.entities.ExperienceDetail;

import java.time.LocalDate;

public record ExperienceDetailResponse (
        Long id,
        String companyName,
        String description,
        LocalDate startDate,
        LocalDate endDate
){
    public static ExperienceDetailResponse fromEntity(ExperienceDetail experienceDetail){
        return new ExperienceDetailResponse(
                experienceDetail.getId(),
                experienceDetail.getCompanyName(),
                experienceDetail.getDescription(),
                experienceDetail.getStartDate(),
                experienceDetail.getEndDate()
        );
    }

}
