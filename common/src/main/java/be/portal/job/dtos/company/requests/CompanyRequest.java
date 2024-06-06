package be.portal.job.dtos.company.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CompanyRequest(

        @NotBlank(message = "The name of the company is required.")
        String name,

        @NotBlank(message = "The website URL of the company is required.")
        String websiteUrl,

        @NotNull(message = "The establishment date of the company is required.")
        LocalDateTime establishmentDate,

        @NotBlank(message = "The contact name of the company is required.")
        String contactName,

        @NotBlank(message = "The contact phone number of the company is required.")
        String contactPhoneNumber,

        @NotBlank(message = "The contact department of the company is required.")
        String contactDepartment
) { }
