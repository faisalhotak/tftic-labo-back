package be.portal.job.dtos.user.responses;

import be.portal.job.entities.JobAdvertiser;
import be.portal.job.entities.JobSeeker;
import be.portal.job.entities.User;
import be.portal.job.exceptions.auth.InvalidUserTypeException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractUserResponse {
    private final String email;
    private final String firstname;
    private final String lastname;
    private final String phoneNumber;
    private final String contactEmail;

    public static AbstractUserResponse fromEntity(User user) {
        return switch (user) {
            case JobAdvertiser jobAdvertiser -> JobAdvertiserResponse.fromEntity(jobAdvertiser);

            case JobSeeker jobSeeker -> JobSeekerResponse.fromEntity(jobSeeker);

            default -> throw new InvalidUserTypeException();
        };
    }
}
