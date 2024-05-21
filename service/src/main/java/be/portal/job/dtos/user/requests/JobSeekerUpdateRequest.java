package be.portal.job.dtos.user.requests;

import be.portal.job.entities.JobSeeker;
import be.portal.job.enums.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class JobSeekerUpdateRequest extends AbstractUserUpdateRequest<JobSeeker> {

    @NotNull(message = "There must be a gender")
    private Gender gender;

    @NotNull(message = "There must be a birth date")
    private LocalDate birthDate;

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

    @Override
    public void updateFields(JobSeeker jobSeeker) {
        super.updateUserFields(jobSeeker);

        jobSeeker.setGender(this.gender);
        jobSeeker.setBirthDate(this.birthDate);
    }
}
