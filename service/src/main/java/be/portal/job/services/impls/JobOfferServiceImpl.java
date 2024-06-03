package be.portal.job.services.impls;

import be.portal.job.dtos.job_offer.requests.JobOfferRequest;
import be.portal.job.dtos.job_offer.responses.JobOfferResponse;
import be.portal.job.entities.*;
import be.portal.job.exceptions.company_advertiser.CompanyAdvertiserNotFoundException;
import be.portal.job.exceptions.company.CompanyNotVerifiedOrActiveException;
import be.portal.job.exceptions.contract_type.ContractTypeNotFoundException;
import be.portal.job.exceptions.job_function.JobFunctionNotFoundException;
import be.portal.job.exceptions.job_offer.JobOfferNotFoundException;
import be.portal.job.mappers.job_offer.JobOfferMapper;
import be.portal.job.repositories.*;
import be.portal.job.services.IJobOfferService;
import be.portal.job.specifications.JobOfferSpecifications;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JobOfferServiceImpl implements IJobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final JobFunctionRepository jobFunctionRepository;
    private final ContractTypeRepository contractTypeRepository;
    private final CompanyAdvertiserRepository companyAdvertiserRepository;
    private final AuthServiceImpl authService;
    private final JobOfferMapper jobOfferMapper;
    private final ApplicationRepository applicationRepository;

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
        return jobOfferRepository.findAllByJobAdvertiser(id).stream()
                .map(jobOfferMapper::fromEntity)
                .toList();
    }

    @Override
    public JobOfferResponse getJobOfferById(Long id) {
        JobOffer jobOffer = jobOfferRepository.findById(id).orElseThrow(JobOfferNotFoundException::new);

        return jobOfferMapper.fromEntity(jobOffer);
    }

    @Override
    @Transactional
    public JobOfferResponse addJobOffer(JobOfferRequest jobOfferRequest) {

        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        CompanyAdvertiser agent = companyAdvertiserRepository
                .findByIdAndJobAdvertiserId(jobOfferRequest.agentId(), currentUser.getId())
                .orElseThrow(CompanyAdvertiserNotFoundException::new);

        if (!agent.getCompany().isVerified() || !agent.getCompany().isActive()) {
            throw new CompanyNotVerifiedOrActiveException();
        }

        ContractType contractType = contractTypeRepository.findById(jobOfferRequest.contractTypeId())
                .orElseThrow(ContractTypeNotFoundException::new);

        JobFunction jobFunction = jobFunctionRepository.findById(jobOfferRequest.jobFunctionId())
                .orElseThrow(JobFunctionNotFoundException::new);

        JobOffer jobOffer = jobOfferMapper.toEntity(jobOfferRequest, agent, contractType, jobFunction);

        return jobOfferMapper.fromEntity(jobOfferRepository.save(jobOffer));
    }

    @Override
    @Transactional
    public JobOfferResponse updateJobOffer(Long id, JobOfferRequest jobOfferRequest) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        JobOffer jobOffer = jobOfferRepository.findByIdAndJobAdvertiserId(id, currentUser.getId())
                .orElseThrow(JobOfferNotFoundException::new);

        ContractType contractType = contractTypeRepository.findById(jobOfferRequest.contractTypeId())
                .orElseThrow(ContractTypeNotFoundException::new);

        JobFunction jobFunction = jobFunctionRepository.findById(jobOfferRequest.jobFunctionId())
                .orElseThrow(JobFunctionNotFoundException::new);

        jobOfferMapper.updateEntityFromRequest(jobOfferRequest, contractType, jobFunction, jobOffer);

        return jobOfferMapper.fromEntity(jobOfferRepository.save(jobOffer));
    }

    @Override
    @Transactional
    public JobOfferResponse deleteJobOffer(Long id) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        JobOffer jobOffer = jobOfferRepository.findByIdAndJobAdvertiserId(id, currentUser.getId())
                .orElseThrow(JobOfferNotFoundException::new);

        applicationRepository.deleteAllByJobOfferId(id);
        jobOfferRepository.deleteById(id);

        return jobOfferMapper.fromEntity(jobOffer);
    }
}
