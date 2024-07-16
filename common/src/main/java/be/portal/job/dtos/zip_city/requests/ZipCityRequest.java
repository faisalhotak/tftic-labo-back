package be.portal.job.dtos.zip_city.requests;

import jakarta.validation.constraints.NotBlank;

public record ZipCityRequest(
        @NotBlank(message = "The city is required.")
        String city,

        @NotBlank(message = "The zip is required.")
        String zip
) { }
