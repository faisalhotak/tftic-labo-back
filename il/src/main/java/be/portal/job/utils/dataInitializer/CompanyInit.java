package be.portal.job.utils.dataInitializer;

import be.portal.job.entities.Company;
import be.portal.job.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(4)
public class CompanyInit implements CommandLineRunner {

    private final CompanyRepository companyRepository;

    @Override
    public void run(String... args) throws Exception {
        // Créer quelques entreprises avec des valeurs arbitraires
        List<Company> companies = List.of(
                new Company("Company A", "http://www.companyA.com", LocalDateTime.now(), "John Doe", 123456789, "Sales", true),
                new Company("Company B", "http://www.companyB.com", LocalDateTime.now(), "Jane Smith", 987654321, "Marketing", true),
                new Company("Company C", "http://www.companyC.com", LocalDateTime.now(), "Alice Johnson", 555555555, "IT", true)
        );

        companyRepository.saveAll(companies);
    }
}
