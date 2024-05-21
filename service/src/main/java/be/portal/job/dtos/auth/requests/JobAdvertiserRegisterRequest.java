package be.portal.job.dtos.auth.requests;

import be.portal.job.entities.JobAdvertiser;
import be.portal.job.entities.Role;
import be.portal.job.enums.UserType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobAdvertiserRegisterRequest extends AbstractRegisterRequest<JobAdvertiser> {

    private static final String ROLE_NAME = UserType.ADVERTISER.name();

    @Override
    public String getRoleName() {
        return ROLE_NAME;
    }

    @Override
    public JobAdvertiser toEntity(Role role) {
        JobAdvertiser jobAdvertiser = new JobAdvertiser();

        fillUserDetails(jobAdvertiser, role);

        return jobAdvertiser;
    }
}
