package be.portal.job.services.impls;

import be.portal.job.dtos.company_advertiser.responses.CompanyAdvertiserResponse;
import be.portal.job.mappers.company_advertiser.CompanyAdvertiserMapper;
import be.portal.job.repositories.CompanyAdvertiserRepository;
import be.portal.job.services.ICompanyAdvertiserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyAdvertiserServiceImpl implements ICompanyAdvertiserService {

    private final CompanyAdvertiserRepository companyAdvertiserRepository;
    private final CompanyAdvertiserMapper companyAdvertiserMapper;

    @Override
    public List<CompanyAdvertiserResponse> getCompanyAdvertiserByJobAdvertiserId(Long jobAdvertiserId) {
        return companyAdvertiserRepository.findByJobAdvertiserId(jobAdvertiserId).stream()
                .map(companyAdvertiserMapper::fromEntity)
                .toList();
    }

}
