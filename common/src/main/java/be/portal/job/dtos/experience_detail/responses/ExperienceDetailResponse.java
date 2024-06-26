package be.portal.job.dtos.experience_detail.responses;

import java.time.LocalDate;

public record ExperienceDetailResponse (
        Long id,
        String companyName,
        String description,
        LocalDate startDate,
        LocalDate endDate
){ }
