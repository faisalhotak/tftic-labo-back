package be.portal.job.dtos.user.responses;

import be.portal.job.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class UserProfileResponse {
    private final Long id;
    private final String email;
    private final String firstname;
    private final String lastname;
    private final String phoneNumber;
    private final String contactEmail;
    private final Address address;
}
