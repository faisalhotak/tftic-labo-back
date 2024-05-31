package be.portal.job.services.impls;

import be.portal.job.dtos.company.requests.CompanyIdRequest;
import be.portal.job.dtos.company.requests.CompanyRequest;
import be.portal.job.dtos.company.responses.CompanyResponse;
import be.portal.job.entities.Company;
import be.portal.job.entities.CompanyAdvertiser;
import be.portal.job.entities.JobAdvertiser;
import be.portal.job.enums.AdvertiserRole;
import be.portal.job.exceptions.NotAllowedException;
import be.portal.job.exceptions.NotFoundException;
import be.portal.job.exceptions.company.CompanyNotFoundException;
import be.portal.job.exceptions.company_advertiser.CompanyAdvertiserInsufficientRole;
import be.portal.job.repositories.*;
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

        if (companyAdvertiserRepository
                .findByCompanyAndJobAdvertiserAndAdvertiserRole(company.getId(), currentUser.getId(), AdvertiserRole.OWNER)
                .isEmpty()
        ) {
            throw new CompanyAdvertiserInsufficientRole();
        }

        companyRequest.updateEntity(company);

        return CompanyResponse.fromEntity(companyRepository.save(company));
    }

    @Override
    @Transactional
    public CompanyResponse deleteCompany(Long id) {
        JobAdvertiser currentUser = authService.getAuthenticatedAdvertiser();

        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);

        if (companyAdvertiserRepository
                .findByCompanyAndJobAdvertiserAndAdvertiserRole(company.getId(), currentUser.getId(), AdvertiserRole.OWNER)
                .isEmpty()
        ) {
            throw new CompanyAdvertiserInsufficientRole();
        }

        List<Long> agentsIds = companyAdvertiserRepository.findAllAgentsIdsByCompany(company.getId());

        jobOfferRepository.deleteByAgentsIds(agentsIds);
        companyAdvertiserRepository.deleteByIds(agentsIds);
        companyRepository.delete(company);

        return CompanyResponse.fromEntity(company);
    }

    @Override
    @Transactional
    public CompanyResponse addCompanyAsAdmin(Long userId, CompanyRequest companyRequest) {
        JobAdvertiser jobAdvertiser = jobAdvertiserRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Job Advertiser has not been found !"));

        Company company = new Company();
        companyRequest.updateEntity(company);
        companyRepository.save(company);

        CompanyAdvertiser companyAdvertiser = new CompanyAdvertiser(AdvertiserRole.OWNER, jobAdvertiser, company);
        companyAdvertiserRepository.save(companyAdvertiser);

        return CompanyResponse.fromEntity(company);
    }

    @Override
    public CompanyResponse updateCompanyAsAdmin(Long id, CompanyRequest companyRequest) {
        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);

        companyRequest.updateEntity(company);

        return CompanyResponse.fromEntity(companyRepository.save(company));
    }

    @Override
    @Transactional
    public CompanyResponse deleteCompanyAsAdmin(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);

        List<Long> agentsIds = companyAdvertiserRepository.findAllAgentsIdsByCompany(company.getId());

        jobOfferRepository.deleteByAgentsIds(agentsIds);
        companyAdvertiserRepository.deleteByIds(agentsIds);
        companyRepository.delete(company);

        return CompanyResponse.fromEntity(company);
    }

    @Override
    public CompanyResponse triggerActive(CompanyIdRequest request, boolean isActive) {
        Company company = companyRepository.findById(request.id()).orElseThrow(CompanyNotFoundException::new);

        if (isActive == company.isActive()) {
            throw new NotAllowedException(String.format("Company field 'isActive' already defined to '%s'", isActive));
        }

        company.setActive(isActive);

        return CompanyResponse.fromEntity(companyRepository.save(company));
    }
}
