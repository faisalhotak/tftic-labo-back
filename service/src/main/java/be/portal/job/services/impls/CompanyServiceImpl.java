package be.portal.job.services.impls;

import be.portal.job.dtos.company.requests.CompanyRequest;
import be.portal.job.dtos.company.responses.CompanyResponse;
import be.portal.job.dtos.company_advertiser.requests.CompanyAdvertiserRequest;
import be.portal.job.dtos.company_advertiser.requests.CompanyAdvertiserUpdateRequest;
import be.portal.job.dtos.company_advertiser.responses.CompanyAdvertiserResponse;
import be.portal.job.entities.*;
import be.portal.job.enums.AdvertiserRole;
import be.portal.job.exceptions.AlreadyExistsException;
import be.portal.job.exceptions.NotAllowedException;
import be.portal.job.exceptions.NotFoundException;
import be.portal.job.exceptions.auth.UserNotFoundException;
import be.portal.job.exceptions.company.CompanyNotFoundException;
import be.portal.job.exceptions.job_offer.JobOfferIsActiveException;
import be.portal.job.exceptions.job_offer.JobOfferNotFoundException;
import be.portal.job.mappers.company_advertiser.CompanyAdvertiserMapper;
import be.portal.job.repositories.CompanyAdvertiserRepository;
import be.portal.job.repositories.CompanyRepository;
import be.portal.job.repositories.JobAdvertiserRepository;
import be.portal.job.repositories.JobOfferRepository;
import be.portal.job.services.ICompanyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyAdvertiserRepository companyAdvertiserRepository;
    private final JobOfferRepository jobOfferRepository;
    private final CompanyAdvertiserMapper companyAdvertiserMapper;
    private final AuthServiceImpl authService;
    private final JobAdvertiserRepository jobAdvertiserRepository;

    @Override
    public List<CompanyResponse> getAll() {
        return companyRepository.findAll().stream()
                .map(CompanyResponse::fromEntity)
                .toList();
    }

    @Override
    public CompanyResponse getCompanyById(Long id) {
        return companyRepository.findById(id)
                .map(CompanyResponse::fromEntity)
                .orElseThrow(CompanyNotFoundException::new);
    }

    @Override
    @Transactional
    public CompanyResponse addCompany(CompanyRequest companyRequest) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        Company company = new Company();
        companyRequest.updateEntity(company);
        companyRepository.save(company);

        CompanyAdvertiser companyAdvertiser = new CompanyAdvertiser(AdvertiserRole.OWNER, currentUser, company);
        companyAdvertiserRepository.save(companyAdvertiser);

        return CompanyResponse.fromEntity(company);
    }

    @Override
    public CompanyResponse updateCompany(Long id, CompanyRequest companyRequest) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);

        CompanyAdvertiser companyAdvertiser = companyAdvertiserRepository
                .findByCompanyAndAgent(company.getId(), currentUser.getId())
                .orElseThrow(() -> new NotFoundException("Agent not found."));

        if (!isOwner(companyAdvertiser) && !authService.isAdmin(currentUser)) {
            throw new NotAllowedException("You are not allowed to update this company.");
        }

        companyRequest.updateEntity(company);

        return CompanyResponse.fromEntity(companyRepository.save(company));
    }

    @Override
    @Transactional
    public CompanyResponse deleteCompany(Long id) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);

        CompanyAdvertiser companyAdvertiser = companyAdvertiserRepository.
                findByCompanyAndAgent(company.getId(), currentUser.getId())
                .orElseThrow(() -> new NotAllowedException("You are not part of this company."));

        if (!isOwner(companyAdvertiser) && !authService.isAdmin(currentUser)) {
            throw new NotAllowedException("You are not allowed to delete this company.");
        }

        List<Long> agentsIds = companyAdvertiserRepository.findAllAgentsIdsByCompany(company.getId());

        jobOfferRepository.deleteByAgentsIds(agentsIds);
        companyAdvertiserRepository.deleteByIds(agentsIds);
        companyRepository.delete(company);

        return CompanyResponse.fromEntity(company);
    }

    @Transactional
    @Override
    public CompanyAdvertiserResponse addAdvertiserToCompany(Long companyId, CompanyAdvertiserRequest request) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        Company company = companyRepository.findById(companyId).orElseThrow(CompanyNotFoundException::new);

        companyAdvertiserRepository.findByCompanyAndAgentIdAndAdvertiserRole(company.getId(), currentUser.getId(), AdvertiserRole.OWNER)
                .orElseThrow(() -> new NotAllowedException("You are not owner of this company."));

        JobAdvertiser jobAdvertiser = jobAdvertiserRepository.findById(request.jobAdvertiserId())
                .orElseThrow(UserNotFoundException::new);

        if (companyAdvertiserRepository.findByCompanyAndAgent(company.getId(), jobAdvertiser.getId()).isPresent()) {
            throw new AlreadyExistsException("Advertiser already exists.");
        }

        CompanyAdvertiser newCompanyAdvertiser = new CompanyAdvertiser(request.advertiserRole(), jobAdvertiser, company);

        return companyAdvertiserMapper.fromEntity(companyAdvertiserRepository.save(newCompanyAdvertiser));
    }

    @Transactional
    @Override
    public CompanyAdvertiserResponse updateCompanyAdvertiser(Long agentId, CompanyAdvertiserUpdateRequest request) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        CompanyAdvertiser companyAdvertiser = companyAdvertiserRepository.findById(agentId)
                .orElseThrow(() -> new NotAllowedException("Company advertiser not found."));

        companyAdvertiserRepository
                .findByCompanyAndAgentIdAndAdvertiserRole(companyAdvertiser.getCompany().getId(), currentUser.getId(), AdvertiserRole.OWNER)
                .orElseThrow(() -> new NotAllowedException("You are not owner of this company."));

       companyAdvertiserMapper.updateEntityFromRequest(request, companyAdvertiser);

        return companyAdvertiserMapper.fromEntity(companyAdvertiserRepository.save(companyAdvertiser));
    }

    @Transactional
    @Override
    public CompanyAdvertiserResponse deleteCompanyAdvertiser(Long agentId) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        CompanyAdvertiser companyAdvertiser = companyAdvertiserRepository.findById(agentId)
                .orElseThrow(() -> new NotAllowedException("This agent does not exist."));

        companyAdvertiserRepository
                .findByCompanyAndAgent(companyAdvertiser.getCompany().getId(), currentUser.getId())
                .orElseThrow(() -> new NotAllowedException("You are not owner of this company."));

        List<JobOffer> jobOffers = jobOfferRepository.findAllByAgentId((companyAdvertiser.getId()));

        if (!jobOffers.isEmpty()) {
            throw new NotAllowedException("This agent has job offers. They need to be transferred to another agent.");
        }

        companyAdvertiserRepository.delete(companyAdvertiser);

        return companyAdvertiserMapper.fromEntity(companyAdvertiser);
    }

    private boolean isOwner(CompanyAdvertiser companyAdvertiser) {
        return companyAdvertiser.getAdvertiserRole().equals(AdvertiserRole.OWNER);
    }
}
