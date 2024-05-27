package be.portal.job.dtos.auth.requests;

import be.portal.job.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class JobAdvertiserRegisterRequest extends AbstractRegisterRequest {

    private static final String ROLE_NAME = UserType.ADVERTISER.name();

    @Override
    public String getRoleName() {
        return ROLE_NAME;
    }
}
