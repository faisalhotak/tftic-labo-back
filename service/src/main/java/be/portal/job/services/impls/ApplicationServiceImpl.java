package be.portal.job.services.impls;

import be.portal.job.dtos.application.requests.ApplicationRequest;
import be.portal.job.dtos.application.requests.ApplicationStatusRequest;
import be.portal.job.dtos.application.requests.ApplicationUpdateRequest;
import be.portal.job.dtos.application.responses.ApplicationResponse;
import be.portal.job.entities.Application;
import be.portal.job.entities.JobOffer;
import be.portal.job.entities.JobSeeker;
import be.portal.job.enums.ApplicationStatus;
import be.portal.job.exceptions.NotAllowedException;
import be.portal.job.exceptions.application.ApplicationNotFoundException;
import be.portal.job.exceptions.application.ApplicationStatusAlreadyDefined;
import be.portal.job.exceptions.job_offer.JobOfferNotFoundException;
import be.portal.job.repositories.ApplicationRepository;
import be.portal.job.repositories.JobOfferRepository;
import be.portal.job.services.IApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements IApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobOfferRepository jobOfferRepository;
    private final AuthServiceImpl authService;

    @Override
    public List<ApplicationResponse> getAll() {
        return applicationRepository.findAll()
                .stream()
                .map(ApplicationResponse::fromEntity)
                .toList();
    }

    @Override
    public List<ApplicationResponse> getAllBySeeker() {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        return applicationRepository.findByJobSeekerId(jobSeeker.getId())
                .stream()
                .map(ApplicationResponse::fromEntity)
                .toList();
    }

    @Override
    public ApplicationResponse getApplicationById(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(ApplicationNotFoundException::new);

        return ApplicationResponse.fromEntity(application);
    }

    @Override
    public ApplicationResponse getApplicationByIdAndJobSeekerId(Long id) {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        Application application = applicationRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(ApplicationNotFoundException::new);

        return ApplicationResponse.fromEntity(applicationRepository.save(application));
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

        Application application = applicationRequest.toEntity(jobSeeker, jobOffer, ApplicationStatus.SUBMITTED);

        return ApplicationResponse.fromEntity(applicationRepository.save(application));
    }

    @Override
    public ApplicationResponse updateApplication(Long id, ApplicationUpdateRequest request) {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        Application application = applicationRepository
                .findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(ApplicationNotFoundException::new);

        application.setApplyDate(request.applyDate());
        application.setApplicationStatus(request.applicationStatus());

        return ApplicationResponse.fromEntity(applicationRepository.save(application));
    }

    @Override
    public ApplicationResponse deleteApplication(Long id) {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        Application application = applicationRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(ApplicationNotFoundException::new);

        applicationRepository.deleteById(id);

        return ApplicationResponse.fromEntity(application);
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

        return ApplicationResponse.fromEntity(applicationRepository.save(application));
    }

    @Override
    public ApplicationResponse triggerApplicationStatus(Long id, ApplicationStatusRequest request) {
        Application application = applicationRepository.findById(id).orElseThrow(ApplicationNotFoundException::new);

        ApplicationStatus status = request.status();

        if (application.getApplicationStatus().equals(status)) {
            throw new ApplicationStatusAlreadyDefined(status);
        }

        application.setApplicationStatus(status);

        return ApplicationResponse.fromEntity(applicationRepository.save(application));
    }
}
