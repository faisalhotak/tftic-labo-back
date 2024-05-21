package be.portal.job.dtos.user.responses;

import be.portal.job.entities.JobSeeker;
import be.portal.job.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class JobSeekerResponse extends AbstractUserResponse {

    private final LocalDate birthDate;
    private final Gender gender;

    public JobSeekerResponse(
            String email,
            String firstname,
            String lastname,
            String phoneNumber,
            String contactEmail,
            LocalDate birthDate,
            Gender gender
    ) {
        super(email, firstname, lastname, phoneNumber, contactEmail);
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public static JobSeekerResponse fromEntity(JobSeeker jobSeeker) {
        return new JobSeekerResponse(
                jobSeeker.getEmail(),
                jobSeeker.getFirstname(),
                jobSeeker.getLastname(),
                jobSeeker.getPhoneNumber(),
                jobSeeker.getContactEmail(),
                jobSeeker.getBirthDate(),
                jobSeeker.getGender()
        );
    }
}
