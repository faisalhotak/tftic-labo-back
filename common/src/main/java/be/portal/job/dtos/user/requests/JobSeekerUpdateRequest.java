package be.portal.job.dtos.user.requests;

import be.portal.job.enums.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class JobSeekerUpdateRequest extends UserUpdateRequest {

    @NotNull(message = "There must be a gender")
    private final Gender gender;

    @NotNull(message = "There must be a birth date")
    private final LocalDate birthDate;

    public JobSeekerUpdateRequest(
            String firstname,
            String lastname,
            String phoneNumber,
            String contactEmail,
            String street,
            String city,
            Integer zip,
            String country,
            Gender gender,
            LocalDate birthDate
    ) {
        super(firstname, lastname, phoneNumber, contactEmail, street, city, zip, country);
        this.gender = gender;
        this.birthDate = birthDate;
    }
}
