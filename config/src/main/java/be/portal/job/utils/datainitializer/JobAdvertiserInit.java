package be.portal.job.utils.datainitializer;

import be.portal.job.entities.JobAdvertiser;
import be.portal.job.entities.Address;
import be.portal.job.entities.Role;
import be.portal.job.repositories.RoleRepository;
import be.portal.job.repositories.JobAdvertiserRepository;
import be.portal.job.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(4)
public class JobAdvertiserInit implements CommandLineRunner {

    private final JobAdvertiserRepository jobAdvertiserRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<Role> userRoles = roleRepository.findAll();

        Role adminRole = userRoles.stream()
                .filter(role -> role.getName().equals(Constants.ADMIN_ROLE))
                .findFirst()
                .orElseThrow();

        Role advertiserRole = userRoles.stream()
                .filter(role -> role.getName().equals(Constants.ADVERTISER_ROLE))
                .findFirst()
                .orElseThrow();

        // Job Advertiser 1
        JobAdvertiser jobAdvertiser1 = new JobAdvertiser();
        jobAdvertiser1.setEmail("advertiser1@example.com");
        jobAdvertiser1.setPassword(passwordEncoder.encode("password123"));
        jobAdvertiser1.setFirstname("John");
        jobAdvertiser1.setLastname("Doe");
        jobAdvertiser1.setPhoneNumber("123456789");
        jobAdvertiser1.setContactEmail("contact1@example.com");
        jobAdvertiser1.setRoles(Set.of(adminRole, advertiserRole));
        jobAdvertiser1.setAddress(new Address(
                "123 Rue de la Liberté",
                "Bruxelles",
                1000,
                "Belgique")
        );

        // Job Advertiser 2
        JobAdvertiser jobAdvertiser2 = new JobAdvertiser();
        jobAdvertiser2.setEmail("advertiser2@example.com");
        jobAdvertiser2.setPassword(passwordEncoder.encode("password456"));
        jobAdvertiser2.setFirstname("Jane");
        jobAdvertiser2.setLastname("Smith");
        jobAdvertiser2.setPhoneNumber("987654321");
        jobAdvertiser2.setContactEmail("contact2@example.com");
        jobAdvertiser2.setRoles(Set.of(advertiserRole));
        jobAdvertiser2.setAddress(new Address(
                "456 Avenue des Roses",
                "Paris",
                75001,
                "France")
        );

        // Job Advertiser 3
        JobAdvertiser jobAdvertiser3 = new JobAdvertiser();
        jobAdvertiser3.setEmail("advertiser3@example.com");
        jobAdvertiser3.setPassword(passwordEncoder.encode("password789"));
        jobAdvertiser3.setFirstname("Michael");
        jobAdvertiser3.setLastname("Johnson");
        jobAdvertiser3.setPhoneNumber("555444333");
        jobAdvertiser3.setContactEmail("contact3@example.com");
        jobAdvertiser3.setRoles(Set.of(advertiserRole));
        jobAdvertiser3.setAddress(new Address(
                "789 Street of Freedom",
                "New York",
                10001,
                "USA")
        );

        // Job Advertiser 4
        JobAdvertiser jobAdvertiser4 = new JobAdvertiser();
        jobAdvertiser4.setEmail("advertiser4@example.com");
        jobAdvertiser4.setPassword(passwordEncoder.encode("passwordabc"));
        jobAdvertiser4.setFirstname("Emily");
        jobAdvertiser4.setLastname("Brown");
        jobAdvertiser4.setPhoneNumber("111222333");
        jobAdvertiser4.setContactEmail("contact4@example.com");
        jobAdvertiser4.setRoles(Set.of(advertiserRole));
        jobAdvertiser4.setAddress(new Address("10 Downing Street", "London", 26568, "UK"));

        // Job Advertiser 5
        JobAdvertiser jobAdvertiser5 = new JobAdvertiser();
        jobAdvertiser5.setEmail("advertiser5@example.com");
        jobAdvertiser5.setPassword(passwordEncoder.encode("passwordxyz"));
        jobAdvertiser5.setFirstname("David");
        jobAdvertiser5.setLastname("Martinez");
        jobAdvertiser5.setPhoneNumber("999888777");
        jobAdvertiser5.setContactEmail("contact5@example.com");
        jobAdvertiser5.setRoles(Set.of(advertiserRole));
        jobAdvertiser5.setAddress(new Address(
                "Kremlin Embankment",
                "Moscow",
                103073,
                "Russia")
        );

        List<JobAdvertiser> jobAdvertisers = List.of(jobAdvertiser1, jobAdvertiser2, jobAdvertiser3, jobAdvertiser4, jobAdvertiser5);

        jobAdvertiserRepository.saveAll(jobAdvertisers);
    }
}
