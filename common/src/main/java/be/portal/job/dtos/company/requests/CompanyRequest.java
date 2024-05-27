package be.portal.job.dtos.company.requests;

import be.portal.job.entities.Company;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record CompanyRequest(

        @NotBlank(message = "The name of the company is required.")
        String name,

        @NotBlank(message = "The website URL of the company is required.")
        String websiteUrl,

        @NotBlank(message = "The establishment date of the company is required.")
        LocalDateTime establishmentDate,

        @NotBlank(message = "The contact name of the company is required.")
        String contactName,

        @NotBlank(message = "The contact phone number of the company is required.")
        String contactPhoneNumber,

        @NotBlank(message = "The contact department of the company is required.")
        String contactDepartment
) {

    public void updateEntity(Company company) {
        company.setName(name);
        company.setWebsiteUrl(websiteUrl);
        company.setEstablishmentDate(establishmentDate);
        company.setContactName(contactName);
        company.setContactPhoneNumber(contactPhoneNumber);
        company.setContactDepartment(contactDepartment);
    }
}
