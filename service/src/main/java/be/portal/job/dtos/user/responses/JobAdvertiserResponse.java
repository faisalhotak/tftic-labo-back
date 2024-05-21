package be.portal.job.dtos.user.responses;

import be.portal.job.entities.JobAdvertiser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobAdvertiserResponse extends AbstractUserResponse {
    public JobAdvertiserResponse(
            String email,
            String firstname,
            String lastname,
            String phoneNumber,
            String contactEmail
    ) {
        super(email, firstname, lastname, phoneNumber, contactEmail);
    }

    public static JobAdvertiserResponse fromEntity(JobAdvertiser jobAdvertiser) {
        return new JobAdvertiserResponse(
                jobAdvertiser.getEmail(),
                jobAdvertiser.getFirstname(),
                jobAdvertiser.getLastname(),
                jobAdvertiser.getPhoneNumber(),
                jobAdvertiser.getContactEmail()
        );
    }
}
