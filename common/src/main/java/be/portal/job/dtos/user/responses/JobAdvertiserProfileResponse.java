package be.portal.job.dtos.user.responses;

import be.portal.job.entities.Address;

public final class JobAdvertiserProfileResponse extends UserProfileResponse {
    public JobAdvertiserProfileResponse(
            Long id,
            String email,
            String firstname,
            String lastname,
            String phoneNumber,
            String contactEmail,
            Address address
    ) {
        super(id, email, firstname, lastname, phoneNumber, contactEmail, address);
    }
}
