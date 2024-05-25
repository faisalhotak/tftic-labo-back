package be.portal.job.dtos.company.responses;

import be.portal.job.entities.Company;

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
    public static CompanyResponse fromEntity(Company company) {
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
