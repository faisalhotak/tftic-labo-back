package be.portal.job.dtos.company.responses;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public record CompanyResponse(
        Long id,
        String name,
        String websiteUrl,
        LocalDateTime establishmentDate,
        String contactName,
        int contactPhoneNumber,
        String contactDepartment,
        boolean isVerified
) {
    public static CompanyResponse fromEntity(be.portal.job.entities.Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getWebsiteUrl(),
                company.getEstablishmentDate(),
                company.getContactName(),
                company.getContactPhoneNumber(),
                company.getContactDepartment(),
                company.isVerified()
        );
    }
}
