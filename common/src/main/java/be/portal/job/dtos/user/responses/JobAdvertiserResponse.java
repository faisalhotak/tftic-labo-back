package be.portal.job.dtos.user.responses;

import be.portal.job.entities.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class JobAdvertiserResponse extends UserResponse {
    public JobAdvertiserResponse(
            Long id,
            String email,
            String firstname,
            String lastname,
            String phoneNumber,
            String contactEmail,
            Address address,
            Set<String> roles,
            boolean isEnabled,
            boolean isLocked
    ) {
        super(
                id,
                email,
                firstname,
                lastname,
                phoneNumber,
                contactEmail,
                address,
                roles,
                isEnabled,
                isLocked
        );
    }
}
