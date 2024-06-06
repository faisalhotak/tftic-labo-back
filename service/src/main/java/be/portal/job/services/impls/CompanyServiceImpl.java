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
import be.portal.job.mappers.company.CompanyMapper;
import be.portal.job.mappers.company_advertiser.CompanyAdvertiserMapper;
import be.portal.job.repositories.CompanyAdvertiserRepository;
import be.portal.job.repositories.CompanyRepository;
import be.portal.job.repositories.JobAdvertiserRepository;
import be.portal.job.repositories.JobOfferRepository;
import be.portal.job.exceptions.company_advertiser.CompanyAdvertiserInsufficientRole;
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
    private final CompanyMapper companyMapper;
    private final AuthServiceImpl authService;
    private final JobAdvertiserRepository jobAdvertiserRepository;

    @Override
    public List<CompanyResponse> getAll() {
        return companyRepository.findAll().stream()
                .map(companyMapper::fromEntity)
                .toList();
    }

    @Override
    public CompanyResponse getCompanyById(Long id) {
        return companyRepository.findById(id)
                .map(companyMapper::fromEntity)
                .orElseThrow(CompanyNotFoundException::new);
    }

    @Override
    @Transactional
    public CompanyResponse addCompany(CompanyRequest companyRequest) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        Company company = new Company();
        companyMapper.updateEntity(companyRequest, company);
        companyRepository.save(company);

        CompanyAdvertiser companyAdvertiser = new CompanyAdvertiser(AdvertiserRole.OWNER, currentUser, company);
        companyAdvertiserRepository.save(companyAdvertiser);

        return companyMapper.fromEntity(company);
    }

    @Override
    public CompanyResponse updateCompany(Long id, CompanyRequest companyRequest) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);

        if (companyAdvertiserRepository
                .findByCompanyAndAgentIdAndAdvertiserRole(company.getId(), currentUser.getId(), AdvertiserRole.OWNER)
                .isEmpty()
        ) {
            throw new CompanyAdvertiserInsufficientRole();
        }

        companyMapper.updateEntity(companyRequest, company);

        return companyMapper.fromEntity(companyRepository.save(company));
    }

    @Override
    @Transactional
    public CompanyResponse deleteCompany(Long id) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);

        if (companyAdvertiserRepository
                .findByCompanyAndAgentIdAndAdvertiserRole(company.getId(), currentUser.getId(), AdvertiserRole.OWNER)
                .isEmpty()
        ) {
            throw new CompanyAdvertiserInsufficientRole();
        }

        List<Long> agentIds = companyAdvertiserRepository.findAllAgentsIdsByCompany(company.getId());

        jobOfferRepository.updateAllActiveByAgentIds(agentIds, false);
        companyAdvertiserRepository.updateAllActiveByIds(agentIds, false);

        company.setActive(false);

        return companyMapper.fromEntity(companyRepository.save(company));
    }

    @Transactional
    @Override
    public CompanyAdvertiserResponse addAdvertiserToCompany(Long companyId, CompanyAdvertiserRequest request) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        Company company = companyRepository.findById(companyId).orElseThrow(CompanyNotFoundException::new);

        if (companyAdvertiserRepository
                .findByCompanyAndAgentIdAndAdvertiserRole(company.getId(), currentUser.getId(), AdvertiserRole.OWNER)
                .isEmpty()) {
            throw new CompanyAdvertiserInsufficientRole();
        }

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

        if (companyAdvertiserRepository
                .findByCompanyAndAgentIdAndAdvertiserRole(companyAdvertiser.getCompany().getId(), currentUser.getId(), AdvertiserRole.OWNER)
                .isEmpty()) {
            throw new CompanyAdvertiserInsufficientRole();
        }

        companyAdvertiserMapper.updateEntityFromRequest(request, companyAdvertiser);

        return companyAdvertiserMapper.fromEntity(companyAdvertiserRepository.save(companyAdvertiser));
    }

    @Transactional
    @Override
    public CompanyAdvertiserResponse deleteCompanyAdvertiser(Long agentId) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        CompanyAdvertiser companyAdvertiser = companyAdvertiserRepository.findById(agentId)
                .orElseThrow(() -> new NotAllowedException("This agent does not exist."));

        if (companyAdvertiserRepository
                .findByCompanyAndAgentIdAndAdvertiserRole(companyAdvertiser.getCompany().getId(), currentUser.getId(), AdvertiserRole.OWNER)
                .isEmpty()) {
            throw new CompanyAdvertiserInsufficientRole();
        }

        jobOfferRepository.updateAllActiveByJobAdvertiserId(companyAdvertiser.getId(), false);

        companyAdvertiser.setActive(false);

        return companyAdvertiserMapper.fromEntity(companyAdvertiserRepository.save(companyAdvertiser));
    }

    @Override
    @Transactional
    public CompanyResponse addCompanyAsAdmin(Long userId, CompanyRequest companyRequest) {
        JobAdvertiser jobAdvertiser = jobAdvertiserRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Job Advertiser has not been found !"));

        Company company = new Company();
        companyMapper.updateEntity(companyRequest, company);
        companyRepository.save(company);

        CompanyAdvertiser companyAdvertiser = new CompanyAdvertiser(AdvertiserRole.OWNER, jobAdvertiser, company);
        companyAdvertiserRepository.save(companyAdvertiser);

        return companyMapper.fromEntity(company);
    }

    @Override
    public CompanyResponse updateCompanyAsAdmin(Long id, CompanyRequest companyRequest) {
        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);

        companyMapper.updateEntity(companyRequest, company);

        return companyMapper.fromEntity(companyRepository.save(company));
    }

    @Override
    @Transactional
    public CompanyResponse deleteCompanyAsAdmin(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);

        List<Long> agentIds = companyAdvertiserRepository.findAllAgentsIdsByCompany(company.getId());

        jobOfferRepository.updateAllActiveByAgentIds(agentIds, false);
        companyAdvertiserRepository.updateAllActiveByIds(agentIds, false);

        company.setActive(false);

        return companyMapper.fromEntity(companyRepository.save(company));
    }

    @Override
    public CompanyResponse triggerActive(Long id, boolean isActive) {
        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);

        if (isActive == company.isActive()) {
            throw new NotAllowedException(String.format("Company field 'isActive' already defined to '%s'", isActive));
        }

        company.setActive(isActive);

        return companyMapper.fromEntity(companyRepository.save(company));
    }
}
