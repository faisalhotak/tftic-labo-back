package be.portal.job.services.impls;

import be.portal.job.dtos.job_offer.requests.JobOfferRequest;
import be.portal.job.dtos.job_offer.requests.JobOfferTransferRequest;
import be.portal.job.dtos.job_offer.responses.JobOfferResponse;
import be.portal.job.dtos.job_offer.responses.PagedJobOfferResponse;
import be.portal.job.entities.*;
import be.portal.job.exceptions.NotAllowedException;
import be.portal.job.exceptions.company_advertiser.CompanyAdvertiserNotFoundException;
import be.portal.job.exceptions.company.CompanyNotActiveException;
import be.portal.job.exceptions.contract_type.ContractTypeNotFoundException;
import be.portal.job.exceptions.job_function.JobFunctionNotFoundException;
import be.portal.job.exceptions.job_offer.JobOfferNotFoundException;
import be.portal.job.mappers.job_offer.JobOfferMapper;
import be.portal.job.repositories.*;
import be.portal.job.services.IJobOfferService;
import be.portal.job.specifications.JobOfferSpecifications;
import be.portal.job.utils.Constants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public PagedJobOfferResponse getAll(Map<String, String> params, int page) {
        Pageable pageable = PageRequest.of(page, Constants.PAGE_SIZE);

        Page<JobOffer> pagedJobOffers = jobOfferRepository
                .findAll(JobOfferSpecifications.filterByParams(params), pageable);

        return new PagedJobOfferResponse(
                pagedJobOffers.map(jobOfferMapper::fromEntity).getContent(),
                pagedJobOffers.getTotalElements(),
                pagedJobOffers.getTotalPages()
        );
    }

    @Override
    public List<JobOfferResponse> getAllByAgent(Long id) {
        return jobOfferRepository.findAllByAgentId(id).stream()
                .map(jobOfferMapper::fromEntity)
                .toList();
    }

    @Override
    public JobOfferResponse getJobOfferById(Long id) {
        JobOffer jobOffer = jobOfferRepository.findById(id).orElseThrow(JobOfferNotFoundException::new);

        return jobOfferMapper.fromEntity(jobOffer);
    }

    @Override
    public List<JobOfferResponse> getAllJobOffersByCompany(Long id) {
        return jobOfferRepository.findByCompanyId(id).stream()
                .map(jobOfferMapper::fromEntity)
                .toList();
    }

    @Override
    public List<String> getAllLocations() {
        return jobOfferRepository.findAllLocation();
    }

    @Override
    @Transactional
    public JobOfferResponse addJobOffer(JobOfferRequest jobOfferRequest) {

        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        CompanyAdvertiser agent = companyAdvertiserRepository
                .findByIdAndJobAdvertiserId(jobOfferRequest.agentId(), currentUser.getId())
                .orElseThrow(CompanyAdvertiserNotFoundException::new);

        if (!agent.getCompany().isActive()) {
            throw new CompanyNotActiveException();
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
    public JobOfferResponse transferJobOffer(Long id, JobOfferTransferRequest jobOfferTransferRequest) {

        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        JobOffer jobOffer = jobOfferRepository.findByIdAndJobAdvertiserId(id, currentUser.getId())
                .orElseThrow(JobOfferNotFoundException::new);

        CompanyAdvertiser colleague = companyAdvertiserRepository
                .findById(jobOfferTransferRequest.agentId())
                .orElseThrow(CompanyAdvertiserNotFoundException::new);

        jobOffer.setAgent(colleague);

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

    @Override
    public JobOfferResponse triggerActive(Long id, boolean isActive) {
        JobOffer jobOffer = jobOfferRepository.findById(id).orElseThrow(JobOfferNotFoundException::new);

        if (isActive == jobOffer.isActive()) {
            throw new NotAllowedException(String.format("Job offer field 'isActive' already defined to '%s'", isActive));
        }

        jobOffer.setActive(isActive);

        return jobOfferMapper.fromEntity(jobOfferRepository.save(jobOffer));
    }
}
