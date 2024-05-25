package be.portal.job.dtos.auth.requests;

import be.portal.job.enums.UserType;

public class JobAdvertiserRegisterRequest extends AbstractRegisterRequest {

    private static final String ROLE_NAME = UserType.ADVERTISER.name();

    @Override
    public String getRoleName() {
        return ROLE_NAME;
    }
}
