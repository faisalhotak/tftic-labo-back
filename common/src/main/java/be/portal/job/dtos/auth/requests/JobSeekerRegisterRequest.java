package be.portal.job.dtos.auth.requests;

import be.portal.job.enums.Gender;
import be.portal.job.enums.UserType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JobSeekerRegisterRequest extends AbstractRegisterRequest {

    private static final String ROLE_NAME = UserType.SEEKER.name();

    @NotNull(message = "There must be a gender")
    private Gender gender;

    @NotNull(message = "There must be a birth date")
    private LocalDate birthDate;

    @Override
    public String getRoleName() {
        return ROLE_NAME;
    }
}
