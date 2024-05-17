package be.portal.job.models.requests;

import be.portal.job.entities.Address;
import be.portal.job.entities.JobAdvertiser;
import be.portal.job.entities.JobSeeker;
import be.portal.job.entities.User;
import be.portal.job.enums.Gender;
import be.portal.job.enums.UserType;
import be.portal.job.exceptions.auth.InvalidUserType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserAddRequest(

        @NotBlank(message = "There must be an email")
        @Email(message = "The email is not valid")
        String email,

        @NotNull(message = "There must be a phone number")
        @Size(min = 8, message = "Password must be 8 characters minimum")
        String password,

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
        LocalDate birthDate,

        @NotNull(message = "There must be a user type, ${validatedValue} is not valid")
        UserType userType
) {
    public User toEntity() {
        User user = null;

        if (userType.name().equals("SEEKER")) {
            JobSeeker jobSeeker = new JobSeeker();

            jobSeeker.setGender(gender);
            jobSeeker.setBirthDate(birthDate);

            user = jobSeeker;
        }

        if (userType.name().equals("ADVERTISER")) {
            user = new JobAdvertiser();
        }

        if (user == null) {
            throw new InvalidUserType();
        }

        user.setEmail(email);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPhoneNumber(phoneNumber);
        user.setContactEmail(contactEmail);

        user.setAddress(new Address(street, city, zip, country));

        return user;
    }
}
