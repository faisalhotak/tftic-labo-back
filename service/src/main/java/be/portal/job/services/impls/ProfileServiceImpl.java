package be.portal.job.services.impls;

import be.portal.job.dtos.user.requests.JobAdvertiserUpdateRequest;
import be.portal.job.dtos.user.requests.JobSeekerUpdateRequest;
import be.portal.job.dtos.user.responses.JobAdvertiserResponse;
import be.portal.job.dtos.user.responses.JobSeekerResponse;
import be.portal.job.entities.*;
import be.portal.job.enums.AdvertiserRole;
import be.portal.job.enums.ApplicationStatus;
import be.portal.job.repositories.*;
import be.portal.job.services.IProfileService;
import be.portal.job.services.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements IProfileService {

    private final CompanyAdvertiserRepository companyAdvertiserRepository;
    private final CompanyRepository companyRepository;
    private final JobOfferRepository jobOfferRepository;
    private final ApplicationRepository applicationRepository;
    private final IUserService userService;
    private final UserRepository userRepository;
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
    public String disableProfile() {
        User currentUser = authService.getAuthenticatedUser();
        currentUser.setEnabled(false);
        currentUser.setCredentialsExpired(true);

        if (currentUser instanceof JobAdvertiser jobAdvertiser) {
            List<CompanyAdvertiser> companyAdvertisers = companyAdvertiserRepository.findAllByAgentIdAndAdvertiserRole(jobAdvertiser.getId(), AdvertiserRole.OWNER);

            if (companyAdvertisers.size() == 1) {
                Company company = companyRepository.findById(companyAdvertisers.getFirst().getCompany().getId()).orElseThrow();
                company.setActive(false);
                companyRepository.save(company);

                List<JobOffer> jobOffers = jobOfferRepository.findAllByCompany(company);
                jobOffers.forEach(jobOffer -> jobOffer.setActive(false));
                jobOfferRepository.saveAll(jobOffers);
            }
            else {
                List<JobOffer> jobOffers = jobOfferRepository.findAllByAgent(jobAdvertiser.getId());
                jobOffers.forEach(jobOffer -> jobOffer.setActive(false));
                jobOfferRepository.saveAll(jobOffers);
            }

        }
        if (currentUser instanceof JobSeeker jobSeeker) {
            List<Application> applications = applicationRepository.findByJobSeekerId(jobSeeker.getId());
            applications.forEach(application -> application.setApplicationStatus(ApplicationStatus.CANCELLED));
            applicationRepository.saveAll(applications);
        }

        userRepository.save(currentUser);
        return "Profile disabled successfully!";
    }

    @Transactional
    @Override
    public String deleteProfile() {
        User currentUser = authService.getAuthenticatedUser();

        if (currentUser.isLocked()) {
            return "Profile already deleted!";
        }

        currentUser.setEmail("deleted");
        currentUser.setFirstname("deleted");
        currentUser.setLastname("deleted");
        currentUser.setPassword("deleted");
        currentUser.setPhoneNumber("deleted");
        currentUser.setContactEmail("deleted");
        currentUser.setLocked(true);

        currentUser.getAddress().setStreet("deleted");


        if (currentUser.isEnabled()) {
            disableProfile();
        } else {
            userRepository.save(currentUser);
        }

        return "Profile deleted successfully!";
    }

}
