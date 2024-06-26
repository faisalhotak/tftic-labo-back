package be.portal.job.dtos.certification_detail.responses;

import java.time.LocalDate;

public record CertificationDetailResponse(
        Long id,
        String name,
        String description,
        LocalDate completionDate
) { }
