package be.portal.job.services.impls;

import be.portal.job.dtos.company.requests.CompanyRequest;
import be.portal.job.dtos.company.responses.CompanyResponse;
import be.portal.job.entities.Company;
import be.portal.job.entities.CompanyAdvertiser;
import be.portal.job.entities.JobAdvertiser;
import be.portal.job.enums.AdvertiserRole;
import be.portal.job.repositories.CompanyAdvertiserRepository;
import be.portal.job.repositories.CompanyRepository;
import be.portal.job.services.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

import static be.portal.job.utils.Constants.ADMIN_ROLE;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyAdvertiserRepository companyAdvertiserRepository;

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
                .orElseThrow();
    }

    @Override
    public CompanyResponse addCompany(CompanyRequest companyRequest) {
        Company company = new Company();
        companyRequest.toEntity(company);
        JobAdvertiser currentUser = (JobAdvertiser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        companyAdvertiserRepository.save(new CompanyAdvertiser(AdvertiserRole.OWNER, currentUser, companyRepository.save(company)));
        return CompanyResponse.fromEntity(company);
    }

    @Override
    public CompanyResponse updateCompany(Long id, CompanyRequest companyRequest) {
        Company company = companyRepository.findById(id).orElseThrow();
        JobAdvertiser currentUser = (JobAdvertiser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        CompanyAdvertiser companyAdvertiser = companyAdvertiserRepository.findByCompanyAndAgent(company.getId(), currentUser.getId());

        if (companyAdvertiser == null || companyAdvertiser.getAdvertiserRole() != AdvertiserRole.OWNER
                && currentUser.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals(ADMIN_ROLE))) {
            throw new RuntimeException("You are not allowed to update this company.");
        }
        companyRequest.toEntity(company);
        return CompanyResponse.fromEntity(companyRepository.save(company));
    }
}
