package be.portal.job.dtos.company.responses;

import java.time.LocalDateTime;

public record CompanyResponse(
        Long id,
        String name,
        String websiteUrl,
        LocalDateTime establishmentDate,
        String contactName,
        String contactPhoneNumber,
        String contactDepartment,
        boolean isActive
) { }
