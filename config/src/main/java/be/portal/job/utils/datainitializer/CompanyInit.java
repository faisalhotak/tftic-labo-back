package be.portal.job.utils.datainitializer;

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
@Order(2)
public class CompanyInit implements CommandLineRunner {

    private final CompanyRepository companyRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create some companies
        List<Company> companies = List.of(
                new Company("Company A", "https://www.companyA.com", LocalDateTime.now(), "John Doe", "123456789", "Sales", true, true),
                new Company("Company B", "https://www.companyB.com", LocalDateTime.now(), "Jane Smith", "987654321", "Marketing", true, true),
                new Company("Company C", "https://www.companyC.com", LocalDateTime.now(), "Alice Johnson", "555555555", "IT", true, true),
                new Company("Tesla", "https://tesla.com", LocalDateTime.parse("2020-01-01T00:00:00"), "Elon Musk", "121010101", "Information Technology", true, false)
        );

        companyRepository.saveAll(companies);
    }
}
