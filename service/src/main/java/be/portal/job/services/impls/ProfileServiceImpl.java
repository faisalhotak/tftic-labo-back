package be.portal.job.services.impls;

import be.portal.job.entities.*;
import be.portal.job.enums.AdvertiserRole;
import be.portal.job.enums.ApplicationStatus;
import be.portal.job.repositories.*;
import be.portal.job.services.IProfileService;
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
    private final UserRepository userRepository;
    private final AuthServiceImpl authService;

    @Transactional
    @Override
    public String disableProfile() {
        User currentUser = authService.getAuthenticatedUser();
        currentUser.setEnabled(false);

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

}
