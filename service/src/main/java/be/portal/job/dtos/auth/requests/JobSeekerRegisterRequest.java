package be.portal.job.dtos.auth.requests;

import be.portal.job.entities.JobSeeker;
import be.portal.job.entities.Role;
import be.portal.job.enums.Gender;
import be.portal.job.enums.UserType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class JobSeekerRegisterRequest extends AbstractRegisterRequest<JobSeeker> {

    private static final String ROLE_NAME = UserType.SEEKER.name();

    @NotNull(message = "There must be a gender")
    private Gender gender;

    @NotNull(message = "There must be a birth date")
    private LocalDate birthDate;

    @Override
    public String getRoleName() {
        return ROLE_NAME;
    }

    @Override
    public JobSeeker toEntity(Role role) {
        JobSeeker jobSeeker = new JobSeeker();

        fillUserDetails(jobSeeker, role);

        jobSeeker.setGender(gender);
        jobSeeker.setBirthDate(birthDate);

        return jobSeeker;
    }
}
