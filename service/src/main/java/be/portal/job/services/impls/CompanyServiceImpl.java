package be.portal.job.services.impls;

import be.portal.job.dtos.company.requests.CompanyRequest;
import be.portal.job.dtos.company.responses.CompanyResponse;
import be.portal.job.entities.Company;
import be.portal.job.entities.CompanyAdvertiser;
import be.portal.job.entities.JobAdvertiser;
import be.portal.job.enums.AdvertiserRole;
import be.portal.job.exceptions.NotAllowedException;
import be.portal.job.exceptions.NotFoundException;
import be.portal.job.exceptions.company.CompanyNotFoundException;
import be.portal.job.repositories.CompanyAdvertiserRepository;
import be.portal.job.repositories.CompanyRepository;
import be.portal.job.repositories.JobOfferRepository;
import be.portal.job.services.ICompanyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static be.portal.job.utils.Constants.ADMIN_ROLE;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyAdvertiserRepository companyAdvertiserRepository;
    private final JobOfferRepository jobOfferRepository;

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
        JobAdvertiser currentUser = (JobAdvertiser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        Company company = new Company();
        companyRequest.updateEntity(company);
        companyRepository.save(company);

        CompanyAdvertiser companyAdvertiser = new CompanyAdvertiser(AdvertiserRole.OWNER, currentUser, company);
        companyAdvertiserRepository.save(companyAdvertiser);

        return CompanyResponse.fromEntity(company);
    }

    @Override
    public CompanyResponse updateCompany(Long id, CompanyRequest companyRequest) {
        JobAdvertiser currentUser = (JobAdvertiser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);

        CompanyAdvertiser companyAdvertiser = companyAdvertiserRepository
                .findByCompanyAndAgent(company.getId(), currentUser.getId())
                .orElseThrow(() -> new NotFoundException("Agent not found."));

        if (companyAdvertiser == null || companyAdvertiser.getAdvertiserRole() != AdvertiserRole.OWNER
                && currentUser.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals(ADMIN_ROLE))) {
            throw new NotAllowedException("You are not allowed to update this company.");
        }

        companyRequest.updateEntity(company);

        return CompanyResponse.fromEntity(companyRepository.save(company));
    }

    @Override
    @Transactional
    public CompanyResponse deleteCompany(Long id) {
        JobAdvertiser currentUser = (JobAdvertiser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);

        CompanyAdvertiser companyAdvertiser = companyAdvertiserRepository.
                findByCompanyAndAgent(company.getId(), currentUser.getId())
                .orElseThrow(() -> new NotAllowedException("You are not part of this company."));

        if (companyAdvertiser == null || companyAdvertiser.getAdvertiserRole() != AdvertiserRole.OWNER
                && currentUser.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals(ADMIN_ROLE))) {
            throw new NotAllowedException("You are not allowed to delete this company.");
        }

        List<Long> agentsIds = companyAdvertiserRepository.findAllAgentsIdsByCompany(company.getId());

        jobOfferRepository.deleteByAgentsIds(agentsIds);
        companyAdvertiserRepository.deleteByIds(agentsIds);
        companyRepository.delete(company);

        return CompanyResponse.fromEntity(company);
    }

}
