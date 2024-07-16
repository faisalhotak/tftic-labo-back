package be.portal.job.services.impls;

import be.portal.job.dtos.application.requests.ApplicationRequest;
import be.portal.job.dtos.application.requests.ApplicationStatusRequest;
import be.portal.job.dtos.application.requests.ApplicationUpdateRequest;
import be.portal.job.dtos.application.responses.ApplicationResponse;
import be.portal.job.dtos.application.responses.PagedApplicationsResponse;
import be.portal.job.entities.*;
import be.portal.job.enums.ApplicationStatus;
import be.portal.job.exceptions.NotAllowedException;
import be.portal.job.exceptions.application.ApplicationNotFoundException;
import be.portal.job.exceptions.application.ApplicationStatusAlreadyDefined;
import be.portal.job.exceptions.company_advertiser.CompanyAdvertiserNotFoundException;
import be.portal.job.exceptions.job_offer.JobOfferNotFoundException;
import be.portal.job.mappers.application.ApplicationMapper;
import be.portal.job.repositories.ApplicationRepository;
import be.portal.job.repositories.CompanyAdvertiserRepository;
import be.portal.job.repositories.JobOfferRepository;
import be.portal.job.services.IApplicationService;
import be.portal.job.specifications.ApplicationSpecifications;
import be.portal.job.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements IApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobOfferRepository jobOfferRepository;
    private final CompanyAdvertiserRepository companyAdvertiserRepository;
    private final ApplicationMapper applicationMapper;
    private final AuthServiceImpl authService;

    @Override
    public PagedApplicationsResponse getAll(Map<String, String> params, int page) {
        Pageable pageable = PageRequest.of(page, Constants.PAGE_SIZE);

        Page<Application> pagedApplications = applicationRepository
                .findAll(ApplicationSpecifications.filterByParams(params), pageable);

        return applicationMapper.fromPage(pagedApplications);
    }

    @Override
    public PagedApplicationsResponse getAllBySeeker(Map<String, String> params, int page) {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();
        params.put("jobSeekerId", jobSeeker.getId().toString());

        Pageable pageable = PageRequest.of(page, Constants.PAGE_SIZE);

        Page<Application> pagedApplications = applicationRepository
                .findAll(ApplicationSpecifications.filterByParams(params), pageable);

        return applicationMapper.fromPage(pagedApplications);
    }

    @Override
    public List<ApplicationResponse> getAllByJobOfferId(Long id) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        Company company = jobOfferRepository.findById(id)
                .orElseThrow(JobOfferNotFoundException::new)
                .getAgent()
                .getCompany();

        if (companyAdvertiserRepository.findByIdAndJobAdvertiserId(company.getId(), currentUser.getId()).isEmpty()) {
            throw new CompanyAdvertiserNotFoundException();
        }

        return applicationRepository.findByJobOfferId(id).stream()
                .map(applicationMapper::fromEntity)
                .toList();
    }

    @Override
    public ApplicationResponse getApplicationById(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(ApplicationNotFoundException::new);

        return applicationMapper.fromEntity(application);
    }

    @Override
    public ApplicationResponse getApplicationByIdAndJobSeekerId(Long id) {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        Application application = applicationRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(ApplicationNotFoundException::new);

        return applicationMapper.fromEntity(applicationRepository.save(application));
    }

    @Override
    public ApplicationResponse addApplication(ApplicationRequest applicationRequest) {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        Optional<Application> existingApplication = applicationRepository
                .findByJobSeekerIdAndJobOfferId(jobSeeker.getId(), applicationRequest.jobOfferId());

        if (existingApplication.isPresent()) {
            throw new NotAllowedException("You have already applied for this job offer.");
        }

        JobOffer jobOffer = jobOfferRepository.findById(applicationRequest.jobOfferId())
                .orElseThrow(JobOfferNotFoundException::new);

        LocalDateTime applyDate = LocalDateTime.now();

        Application application = applicationMapper.toEntity(applyDate, jobSeeker, jobOffer, ApplicationStatus.SUBMITTED);

        return applicationMapper.fromEntity(applicationRepository.save(application));
    }

    @Override
    public ApplicationResponse updateApplication(Long id, ApplicationUpdateRequest request) {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        Application application = applicationRepository
                .findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(ApplicationNotFoundException::new);

        applicationMapper.updateEntityFromRequest(request, application);

        return applicationMapper.fromEntity(applicationRepository.save(application));
    }

    @Override
    public ApplicationResponse deleteApplication(Long id) {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        Application application = applicationRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(ApplicationNotFoundException::new);

        applicationRepository.deleteById(id);

        return applicationMapper.fromEntity(application);
    }

    @Override
    public ApplicationResponse cancelApplication(Long id) {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        Application application = applicationRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(ApplicationNotFoundException::new);

        if (application.getApplicationStatus().equals(ApplicationStatus.CANCELLED)) {
            throw new NotAllowedException("Application is already cancelled.");
        }

        application.setApplicationStatus(ApplicationStatus.CANCELLED);

        return applicationMapper.fromEntity(applicationRepository.save(application));
    }

    @Override
    public ApplicationResponse triggerApplicationStatus(Long id, ApplicationStatusRequest request) {
        Application application = applicationRepository.findById(id).orElseThrow(ApplicationNotFoundException::new);

        ApplicationStatus status = request.status();

        if (application.getApplicationStatus().equals(status)) {
            throw new ApplicationStatusAlreadyDefined(status);
        }

        application.setApplicationStatus(status);

        return applicationMapper.fromEntity(applicationRepository.save(application));
    }
}
