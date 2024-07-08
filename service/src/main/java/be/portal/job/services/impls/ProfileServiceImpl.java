package be.portal.job.services.impls;

import be.portal.job.dtos.user.requests.JobAdvertiserUpdateRequest;
import be.portal.job.dtos.user.requests.JobSeekerUpdateRequest;
import be.portal.job.dtos.user.responses.*;
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

    private final CompanyRepository companyRepository;
    private final JobOfferRepository jobOfferRepository;
    private final ApplicationRepository applicationRepository;
    private final IUserService userService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthServiceImpl authService;

    @Override
    public JobSeekerProfileResponse getJobSeekerProfile() {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        return userMapper.fromJobSeekerProfile(jobSeeker);
    }

    @Override
    public JobAdvertiserProfileResponse getJobAdvertiserProfile() {
        JobAdvertiser jobAdvertiser = authService.getAuthenticatedAdvertiser();

        return userMapper.fromJobAdvertiserProfile(jobAdvertiser);
    }

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
    public UserResponse disableSelf() {
        User currentUser = authService.getAuthenticatedUser();

        return disableProfile(currentUser);
    }

    @Transactional
    @Override
    public UserResponse disableProfile(User user) {
        user.setEnabled(false);

        if (user instanceof JobSeeker jobSeeker) {
            applicationRepository.updateAllStatusByJobSeekerId(jobSeeker.getId(), ApplicationStatus.CANCELLED);

            return userMapper.fromUser(userRepository.save(user));
        }

        if (user instanceof JobAdvertiser jobAdvertiser) {
            // Set all job offers of the current user to inactive
            jobOfferRepository.updateAllActiveByJobAdvertiserId(jobAdvertiser.getId(), false);

            //companies where the current user is the only owner
            List<Long> companiesIds = companyRepository.findCompaniesWithSingleOwnerByJobAdvertiserId(jobAdvertiser.getId(), AdvertiserRole.OWNER);

            companyRepository.setInactiveForCompanies(companiesIds);
            jobOfferRepository.setInactiveForJobOffersByCompaniesIds(companiesIds);

            return userMapper.fromUser(userRepository.save(user));
        }

        throw new IllegalStateException("User is not a job seeker or job advertiser!");
    }

    @Transactional
    @Override
    public UserResponse deleteSelf() {
        User currentUser = authService.getAuthenticatedUser();

        return deleteProfile(currentUser);
    }

    @Transactional
    @Override
    public UserResponse deleteProfile(User user) {
        if (user.isExpired()) {
            throw new IllegalStateException("Profile is already deleted!");
        }

        final String DELETED_STRING = "deleted";

        user.setEmail(UUID.randomUUID().toString());
        user.setFirstname(DELETED_STRING);
        user.setLastname(DELETED_STRING);
        user.setPassword(DELETED_STRING);
        user.setPhoneNumber(DELETED_STRING);
        user.setContactEmail(DELETED_STRING);
        user.setLocked(true);

        user.getAddress().setStreet(DELETED_STRING);

        if (user.isEnabled()) {
            disableProfile(user);
        }

        return userMapper.fromUser(userRepository.save(user));
    }

    @Transactional
    @Override
    public UserResponse deleteUserAsAdmin(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        return deleteProfile(user);
    }
}
