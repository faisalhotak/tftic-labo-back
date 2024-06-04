package be.portal.job.services.impls;

import be.portal.job.dtos.user.requests.JobAdvertiserUpdateRequest;
import be.portal.job.dtos.user.requests.JobSeekerUpdateRequest;
import be.portal.job.dtos.user.responses.JobAdvertiserResponse;
import be.portal.job.dtos.user.responses.JobSeekerResponse;
import be.portal.job.dtos.user.responses.UserResponse;
import be.portal.job.entities.*;
import be.portal.job.enums.AdvertiserRole;
import be.portal.job.enums.ApplicationStatus;
import be.portal.job.mappers.user.UserMapper;
import be.portal.job.repositories.*;
import be.portal.job.services.IProfileService;
import be.portal.job.services.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements IProfileService {

    private final CompanyAdvertiserRepository companyAdvertiserRepository;
    private final CompanyRepository companyRepository;
    private final JobOfferRepository jobOfferRepository;
    private final ApplicationRepository applicationRepository;
    private final IUserService userService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthServiceImpl authService;

    @Override
    public JobSeekerResponse updateJobSeekerProfile(JobSeekerUpdateRequest jobSeekerUpdateRequest) {
        User currentUser = authService.getAuthenticatedUser();

        return userService.updateSeeker(currentUser.getId(), jobSeekerUpdateRequest);
    }

    @Override
    public JobAdvertiserResponse updateJobAdvertiserProfile(JobAdvertiserUpdateRequest jobAdvertiserUpdateRequest) {
        User currentUser = authService.getAuthenticatedUser();

        return userService.updateAdvertiser(currentUser.getId(), jobAdvertiserUpdateRequest);
    }

    @Transactional
    @Override
    public UserResponse disableProfile() {
        User currentUser = authService.getAuthenticatedUser();
        currentUser.setEnabled(false);

        if (currentUser instanceof JobSeeker jobSeeker) {
            applicationRepository.updateAllStatusByJobSeekerId(jobSeeker.getId(), ApplicationStatus.CANCELLED);

            return userMapper.fromUser(userRepository.save(currentUser));
        }

        if (currentUser instanceof JobAdvertiser jobAdvertiser) {
            // Set all job offers of the current user to inactive
            jobOfferRepository.updateAllActiveByJobAdvertiserId(jobAdvertiser.getId(), false);

            //companies where the current user is the only owner
            List<Long> companiesIds = companyRepository.findCompaniesWithSingleOwnerByJobAdvertiserId(jobAdvertiser.getId(), AdvertiserRole.OWNER);

            companyRepository.setInactiveForCompanies(companiesIds);
            jobOfferRepository.setInactiveForJobOffersByCompaniesIds(companiesIds);

            return userMapper.fromUser(userRepository.save(currentUser));
        }

        throw new IllegalStateException("User is not a job seeker or job advertiser!");
    }

    @Transactional
    @Override
    public UserResponse deleteProfile() {
        User currentUser = authService.getAuthenticatedUser();

        if (currentUser.isExpired()) {
            throw new IllegalStateException("Profile is already deleted!");
        }

        currentUser.setEmail(UUID.randomUUID().toString());
        currentUser.setFirstname("deleted");
        currentUser.setLastname("deleted");
        currentUser.setPassword("deleted");
        currentUser.setPhoneNumber("deleted");
        currentUser.setContactEmail("deleted");
        currentUser.setLocked(true);

        currentUser.getAddress().setStreet("deleted");


        if (currentUser.isEnabled()) {
            disableProfile();
        }

        return userMapper.fromUser(userRepository.save(currentUser));
    }

}
