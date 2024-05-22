package be.portal.job.services.impls;

import be.portal.job.dtos.jobOffer.requests.JobOfferPostRequest;
import be.portal.job.dtos.jobOffer.responses.JobOfferResponse;
import be.portal.job.entities.JobAdvertiser;
import be.portal.job.entities.JobOffer;
import be.portal.job.repositories.CompanyAdvertiserRepository;
import be.portal.job.repositories.ContractTypeRepository;
import be.portal.job.repositories.JobFunctionRepository;
import be.portal.job.repositories.JobOfferRepository;
import be.portal.job.services.JobOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static be.portal.job.utils.Constants.ADMIN_ROLE;

@Service
@RequiredArgsConstructor
public class JobOfferServiceImpl implements JobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final JobFunctionRepository jobFunctionRepository;
    private final ContractTypeRepository contractTypeRepository;
    private final CompanyAdvertiserRepository companyAdvertiserRepository;

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
        return jobOfferRepository.findById(id)
                .map(jobOffer -> {
                    JobAdvertiser currentUser = (JobAdvertiser) SecurityContextHolder.getContext()
                            .getAuthentication().getPrincipal();

                    if (!Objects.equals(currentUser.getId(), jobOffer.getAgent().getJobAdvertiser().getId())
                            && currentUser.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals(ADMIN_ROLE))) {
                        throw new RuntimeException("You are not allowed to delete job offers for other job advertisers");
                    }
                    jobOfferRepository.delete(jobOffer);
                    return JobOfferResponse.fromEntity(jobOffer);
                })
                .orElseThrow();
    }

    @Override
    public JobOfferResponse addJobOffer(JobOfferPostRequest jobOfferPostRequest) {
        JobOffer jobOffer = new JobOffer();
        jobOfferPostRequest.toEntity(jobOffer);
        jobOffer.setAgent(companyAdvertiserRepository.findById(jobOfferPostRequest.agentId()).orElseThrow());
        JobAdvertiser currentUser = (JobAdvertiser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (!Objects.equals(currentUser.getId(), jobOffer.getAgent().getJobAdvertiser().getId())
                && currentUser.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals(ADMIN_ROLE))) {
            throw new RuntimeException("You are not allowed to create job offers for other job advertisers");
        }
        jobOffer.setJobFunction(jobFunctionRepository.findById(jobOfferPostRequest.jobFunctionId()).orElseThrow());
        jobOffer.setContractType(contractTypeRepository.findById(jobOfferPostRequest.contractTypeId()).orElseThrow());
        return JobOfferResponse.fromEntity(jobOfferRepository.save(jobOffer));
    }

    @Override
    public JobOfferResponse updateJobOffer(Long id, JobOfferPostRequest jobOfferPostRequest) {
        JobOffer jobOffer = jobOfferRepository.findById(id).orElseThrow();
        jobOfferPostRequest.toEntity(jobOffer);
        JobAdvertiser currentUser = (JobAdvertiser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if (!Objects.equals(currentUser.getId(), jobOffer.getAgent().getJobAdvertiser().getId())
                && currentUser.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals(ADMIN_ROLE))) {
            throw new RuntimeException("You are not allowed to update job offers for other job advertisers");
        }
        jobOffer.setAgent(companyAdvertiserRepository.findById(jobOfferPostRequest.agentId()).orElseThrow());
        jobOffer.setJobFunction(jobFunctionRepository.findById(jobOfferPostRequest.jobFunctionId()).orElseThrow());
        jobOffer.setContractType(contractTypeRepository.findById(jobOfferPostRequest.contractTypeId()).orElseThrow());
        return JobOfferResponse.fromEntity(jobOfferRepository.save(jobOffer));
    }
}
