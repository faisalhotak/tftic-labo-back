package be.portal.job.services.impls;

import be.portal.job.dtos.company.responses.CompanyResponse;
import be.portal.job.repositories.CompanyRepository;
import be.portal.job.services.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<CompanyResponse> getAll() {
        return companyRepository.findAll().stream()
                .map(CompanyResponse::fromEntity)
                .toList();
    }
}
