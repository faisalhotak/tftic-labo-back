package be.portal.job.dtos.user.requests;

import be.portal.job.entities.JobAdvertiser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobAdvertiserUpdateRequest extends AbstractUserUpdateRequest<JobAdvertiser> {
    public JobAdvertiserUpdateRequest(
            String firstname,
            String lastname,
            String phoneNumber,
            String contactEmail,
            String street,
            String city,
            Integer zip,
            String country
    ) {
        super(firstname, lastname, phoneNumber, contactEmail, street, city, zip, country);
    }

    @Override
    public void updateFields(JobAdvertiser jobAdvertiser) {
        super.updateUserFields(jobAdvertiser);
    }
}
