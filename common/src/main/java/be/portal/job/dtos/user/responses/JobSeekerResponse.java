package be.portal.job.dtos.user.responses;

import be.portal.job.entities.Address;
import be.portal.job.enums.Gender;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Getter
public class JobSeekerResponse extends UserResponse {

    private final LocalDate birthDate;
    private final Gender gender;

    public JobSeekerResponse(
            String email,
            String firstname,
            String lastname,
            String phoneNumber,
            String contactEmail,
            Address address,
            LocalDate birthDate,
            Gender gender,
            Set<String> roles
    ) {
        super(
                email,
                firstname,
                lastname,
                phoneNumber,
                contactEmail,
                address,
                roles
        );
        this.birthDate = birthDate;
        this.gender = gender;
    }
}
