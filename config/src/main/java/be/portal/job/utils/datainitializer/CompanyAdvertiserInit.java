package be.portal.job.utils.datainitializer;

import be.portal.job.entities.Company;
import be.portal.job.entities.CompanyAdvertiser;
import be.portal.job.entities.JobAdvertiser;
import be.portal.job.enums.AdvertiserRole;
import be.portal.job.repositories.CompanyAdvertiserRepository;
import be.portal.job.repositories.CompanyRepository;
import be.portal.job.repositories.JobAdvertiserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(10)
public class CompanyAdvertiserInit implements CommandLineRunner {

    private final JobAdvertiserRepository jobAdvertiserRepository;
    private final CompanyRepository companyRepository;
    private final CompanyAdvertiserRepository companyAdvertiserRepository;

    @Override
    public void run(String... args) throws Exception {
        List<JobAdvertiser> jobAdvertisers = jobAdvertiserRepository.findAll();
        List<Company> companies = companyRepository.findAll();

        List<CompanyAdvertiser> companyAdvertisers = List.of(
                new CompanyAdvertiser(AdvertiserRole.OWNER, jobAdvertisers.get(0), companies.get(0)),
                new CompanyAdvertiser(AdvertiserRole.EMPLOYEE, jobAdvertisers.get(1), companies.get(1)),
                new CompanyAdvertiser(AdvertiserRole.PARTNER, jobAdvertisers.get(2), companies.get(2)),
                new CompanyAdvertiser(AdvertiserRole.OWNER, jobAdvertisers.get(1), companies.get(0)),
                new CompanyAdvertiser(AdvertiserRole.OWNER, jobAdvertisers.get(0), companies.get(3)),
                new CompanyAdvertiser(AdvertiserRole.OWNER, jobAdvertisers.get(2), companies.get(1))
        );

        companyAdvertiserRepository.saveAll(companyAdvertisers);
    }
}
