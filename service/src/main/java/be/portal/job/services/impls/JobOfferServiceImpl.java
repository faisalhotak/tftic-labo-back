package be.portal.job.services.impls;

import be.portal.job.dtos.job_offer.requests.JobOfferRequest;
import be.portal.job.dtos.job_offer.responses.JobOfferResponse;
import be.portal.job.entities.JobAdvertiser;
import be.portal.job.entities.JobOffer;
import be.portal.job.exceptions.NotAllowedException;
import be.portal.job.repositories.CompanyAdvertiserRepository;
import be.portal.job.repositories.ContractTypeRepository;
import be.portal.job.repositories.JobFunctionRepository;
import be.portal.job.repositories.JobOfferRepository;
import be.portal.job.services.IJobOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JobOfferServiceImpl implements IJobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final JobFunctionRepository jobFunctionRepository;
    private final ContractTypeRepository contractTypeRepository;
    private final CompanyAdvertiserRepository companyAdvertiserRepository;
    private final AuthServiceImpl authService;

    @Override
    public List<JobOfferResponse> getAll() {
        return jobOfferRepository.findAll().stream()
                .map(JobOfferResponse::fromEntity)
                .toList();
    }

    @Override
    public JobOfferResponse getJobOfferById(Long id) {
        return jobOfferRepository.findById(id)
                .map(JobOfferResponse::fromEntity)
                .orElseThrow();
    }

    @Override
    public List<JobOfferResponse> getAllByAgent(Long id) {
        return jobOfferRepository.findAllByAgent(id).stream()
                .map(JobOfferResponse::fromEntity)
                .toList();
    }

    @Override
    public JobOfferResponse deleteJobOffer(Long id) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        JobOffer jobOffer = jobOfferRepository.findById(id).orElseThrow();

        if (!Objects.equals(currentUser.getId(), jobOffer.getAgent().getJobAdvertiser().getId())
                && !authService.isAdmin(currentUser)) {
            throw new NotAllowedException("You are not allowed to delete job offers for other job advertisers");
        }
        
        jobOfferRepository.delete(jobOffer);
        
        return JobOfferResponse.fromEntity(jobOffer);
    }

    @Override
    public JobOfferResponse addJobOffer(JobOfferRequest jobOfferRequest) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        JobOffer jobOffer = new JobOffer();
        jobOfferRequest.updateEntity(jobOffer);
        jobOffer.setAgent(companyAdvertiserRepository.findById(jobOfferRequest.agentId()).orElseThrow());

        if (!Objects.equals(currentUser.getId(), jobOffer.getAgent().getJobAdvertiser().getId())
                && !authService.isAdmin(currentUser)) {
            throw new NotAllowedException("You are not allowed to create job offers for other job advertisers");
        }

        jobOffer.setJobFunction(jobFunctionRepository.findById(jobOfferRequest.jobFunctionId()).orElseThrow());
        jobOffer.setContractType(contractTypeRepository.findById(jobOfferRequest.contractTypeId()).orElseThrow());

        return JobOfferResponse.fromEntity(jobOfferRepository.save(jobOffer));
    }

    @Override
    public JobOfferResponse updateJobOffer(Long id, JobOfferRequest jobOfferRequest) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        JobOffer jobOffer = jobOfferRepository.findById(id).orElseThrow();
        jobOfferRequest.updateEntity(jobOffer);

        if (!Objects.equals(currentUser.getId(), jobOffer.getAgent().getJobAdvertiser().getId())
                && !authService.isAdmin(currentUser)) {
            throw new NotAllowedException("You are not allowed to update job offers for other job advertisers");
        }
        
        jobOffer.setAgent(companyAdvertiserRepository.findById(jobOfferRequest.agentId()).orElseThrow());
        jobOffer.setJobFunction(jobFunctionRepository.findById(jobOfferRequest.jobFunctionId()).orElseThrow());
        jobOffer.setContractType(contractTypeRepository.findById(jobOfferRequest.contractTypeId()).orElseThrow());

        return JobOfferResponse.fromEntity(jobOfferRepository.save(jobOffer));
    }
}
