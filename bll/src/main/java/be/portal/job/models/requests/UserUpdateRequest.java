package be.portal.job.models.requests;

import be.portal.job.enums.Gender;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserUpdateRequest(

        @NotBlank(message = "There must be a firstname")
        String firstname,

        @NotBlank(message = "There must be lastname")
        String lastname,

        @NotNull(message = "There must be a phone number")
        Integer phoneNumber,

        @NotBlank(message = "There must be a contact email")
        @Email(message = "The contact email is not valid")
        String contactEmail,

        @NotBlank(message = "There must be a street")
        String street,

        @NotBlank(message = "There must be a city")
        String city,

        @NotNull(message = "There must be a zip")
        Integer zip,

        @NotBlank(message = "There must be a country")
        String country,

        @Nullable
        Gender gender,

        @Nullable
        LocalDate birthDate
) { }
