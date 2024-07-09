package be.portal.job.dtos.user.responses;

import be.portal.job.entities.Address;
import be.portal.job.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public final class JobSeekerProfileResponse extends UserProfileResponse {
    private final LocalDate birthDate;
    private final Gender gender;

    public JobSeekerProfileResponse(
            Long id,
            String email,
            String firstname,
            String lastname,
            String phoneNumber,
            String contactEmail,
            Address address,
            LocalDate birthDate,
            Gender gender
    ) {
        super(id, email, firstname, lastname, phoneNumber, contactEmail, address);
        this.birthDate = birthDate;
        this.gender = gender;
    }
}
