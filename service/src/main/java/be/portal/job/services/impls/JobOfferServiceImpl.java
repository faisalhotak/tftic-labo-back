package be.portal.job.services.impls;

import be.portal.job.dtos.job_offer.requests.JobOfferRequest;
import be.portal.job.dtos.job_offer.responses.JobOfferResponse;
import be.portal.job.entities.JobAdvertiser;
import be.portal.job.entities.JobOffer;
import be.portal.job.exceptions.NotAllowedException;
import be.portal.job.mappers.job_offer.JobOfferMapper;
import be.portal.job.repositories.CompanyAdvertiserRepository;
import be.portal.job.repositories.ContractTypeRepository;
import be.portal.job.repositories.JobFunctionRepository;
import be.portal.job.repositories.JobOfferRepository;
import be.portal.job.services.IJobOfferService;
import be.portal.job.specifications.JobOfferSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JobOfferServiceImpl implements IJobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final JobFunctionRepository jobFunctionRepository;
    private final ContractTypeRepository contractTypeRepository;
    private final CompanyAdvertiserRepository companyAdvertiserRepository;
    private final AuthServiceImpl authService;
    private final JobOfferMapper jobOfferMapper;

    @Override
    public List<JobOfferResponse> getAll(Map<String, String> params) {
        return jobOfferRepository
                .findAll(JobOfferSpecifications.filterByParams(params))
                .stream()
                .map(jobOfferMapper::fromEntity)
                .toList();
    }

    @Override
    public List<JobOfferResponse> getAllByAgent(Long id) {
        return jobOfferRepository.findAllByAgent(id).stream()
                .map(jobOfferMapper::fromEntity)
                .toList();
    }

    @Override
    public JobOfferResponse getJobOfferById(Long id) {
        return jobOfferRepository.findById(id)
                .map(jobOfferMapper::fromEntity)
                .orElseThrow();
    }

    @Override
    public JobOfferResponse addJobOffer(JobOfferRequest jobOfferRequest) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        JobOffer jobOffer = new JobOffer();

        jobOfferMapper.updateEntityFromRequest(jobOfferRequest, jobOffer);

        jobOffer.setAgent(companyAdvertiserRepository.findById(jobOfferRequest.agentId()).orElseThrow());

        if (!Objects.equals(currentUser.getId(), jobOffer.getAgent().getJobAdvertiser().getId())
                && !authService.isAdmin(currentUser)) {
            throw new NotAllowedException("You are not allowed to create job offers for other job advertisers");
        }

        jobOffer.setJobFunction(jobFunctionRepository.findById(jobOfferRequest.jobFunctionId()).orElseThrow());
        jobOffer.setContractType(contractTypeRepository.findById(jobOfferRequest.contractTypeId()).orElseThrow());

        return jobOfferMapper.fromEntity(jobOfferRepository.save(jobOffer));
    }

    @Override
    public JobOfferResponse updateJobOffer(Long id, JobOfferRequest jobOfferRequest) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        JobOffer jobOffer = jobOfferRepository.findById(id).orElseThrow();

        if (!Objects.equals(currentUser.getId(), jobOffer.getAgent().getJobAdvertiser().getId())
                && !authService.isAdmin(currentUser)) {
            throw new NotAllowedException("You are not allowed to update job offers for other job advertisers");
        }

        jobOfferMapper.updateEntityFromRequest(jobOfferRequest, jobOffer);

        jobOffer.setAgent(companyAdvertiserRepository.findById(jobOfferRequest.agentId()).orElseThrow());
        jobOffer.setJobFunction(jobFunctionRepository.findById(jobOfferRequest.jobFunctionId()).orElseThrow());
        jobOffer.setContractType(contractTypeRepository.findById(jobOfferRequest.contractTypeId()).orElseThrow());

        return jobOfferMapper.fromEntity(jobOfferRepository.save(jobOffer));
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

        return jobOfferMapper.fromEntity(jobOffer);
    }
}
