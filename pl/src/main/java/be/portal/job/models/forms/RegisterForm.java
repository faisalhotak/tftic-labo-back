package be.portal.job.models.forms;

import be.portal.job.entities.JobAdvertiser;
import be.portal.job.entities.JobSeeker;
import be.portal.job.entities.User;
import be.portal.job.enums.Gender;
import be.portal.job.enums.UserType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record RegisterForm(

        @NotBlank(message = "There must be an email")
        @Email(message = "The email is not valid")
        String email,

        @Size(min = 8, message = "Password must be 8 characters minimum")
        String password,

        @NotBlank(message = "There must be a firstname")
        String firstname,

        @NotBlank(message = "There must be lastname")
        String lastname,

        @NotBlank(message = "There must be a phone number")
        int phoneNumber,

        @NotBlank(message = "There must be a contact email")
        @Email(message = "The contact email is not valid")
        String contactEmail,

        @Nullable
        Gender gender,

        @Nullable
        LocalDateTime birthDate,

        @NotBlank(message = "There must be a UserType")
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
                        throw new RuntimeException("The user type is invalid !");
                }

                user.setEmail(email);
                user.setPassword(password);
                user.setFirstname(firstname);
                user.setLastname(lastname);
                user.setPhoneNumber(phoneNumber);
                user.setContactEmail(contactEmail);

                return user;
        }
}
