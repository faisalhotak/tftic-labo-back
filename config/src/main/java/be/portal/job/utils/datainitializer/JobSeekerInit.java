package be.portal.job.utils.datainitializer;

import be.portal.job.entities.Address;
import be.portal.job.entities.JobSeeker;
import be.portal.job.entities.Role;
import be.portal.job.enums.Gender;
import be.portal.job.repositories.JobSeekerRepository;
import be.portal.job.repositories.RoleRepository;
import be.portal.job.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(7)
public class JobSeekerInit implements CommandLineRunner {

    private final JobSeekerRepository jobSeekerRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<Role> userRoles = roleRepository.findAll();

        Role adminRole = userRoles.stream()
                .filter(role -> role.getName().equals(Constants.ADMIN_ROLE))
                .findFirst()
                .orElseThrow();

        Role seekerRole = userRoles.stream()
                .filter(role -> role.getName().equals(Constants.SEEKER_ROLE))
                .findFirst()
                .orElseThrow();


        JobSeeker jobSeeker1 = new JobSeeker();
        jobSeeker1.setEmail("seeker1@example.com");
        jobSeeker1.setPassword(passwordEncoder.encode("password123"));
        jobSeeker1.setFirstname("John");
        jobSeeker1.setLastname("Doe");
        jobSeeker1.setPhoneNumber("123456789");
        jobSeeker1.setContactEmail("contact1@example.com");
        jobSeeker1.setRoles(Set.of(adminRole, seekerRole));
        jobSeeker1.setAddress(new Address(
                "123 Rue de la Liberté",
                "Bruxelles",
                1000,
                "Belgique")
        );
        jobSeeker1.setBirthDate(LocalDate.of(1990, 1, 1));
        jobSeeker1.setGender(Gender.M);

        JobSeeker jobSeeker2 = new JobSeeker();
        jobSeeker2.setEmail("seeker2@example.com");
        jobSeeker2.setPassword(passwordEncoder.encode("password456"));
        jobSeeker2.setFirstname("Jane");
        jobSeeker2.setLastname("Smith");
        jobSeeker2.setPhoneNumber("987654321");
        jobSeeker2.setContactEmail("contact2@example.com");
        jobSeeker2.setRoles(Set.of(seekerRole));
        jobSeeker2.setAddress(new Address(
                "456 Avenue des Roses",
                "Paris",
                75001,
                "France")
        );
        jobSeeker2.setBirthDate(LocalDate.of(1995, 5, 5));
        jobSeeker2.setGender(Gender.F);

        JobSeeker jobSeeker3 = new JobSeeker();
        jobSeeker3.setEmail("seeker3@example.com");
        jobSeeker3.setPassword(passwordEncoder.encode("password789"));
        jobSeeker3.setFirstname("Michael");
        jobSeeker3.setLastname("Johnson");
        jobSeeker3.setPhoneNumber("555444333");
        jobSeeker3.setContactEmail("contact3@example.com");
        jobSeeker3.setRoles(Set.of(seekerRole));
        jobSeeker3.setAddress(new Address(
                "789 Street of Freedom",
                "New York",
                10001,
                "USA")
        );
        jobSeeker3.setBirthDate(LocalDate.of(1985, 10, 10));
        jobSeeker3.setGender(Gender.X);

        JobSeeker jobSeeker4 = new JobSeeker();
        jobSeeker4.setEmail("seeker4@example.com");
        jobSeeker4.setPassword(passwordEncoder.encode("passwordabc"));
        jobSeeker4.setFirstname("Emily");
        jobSeeker4.setLastname("Brown");
        jobSeeker4.setPhoneNumber("111222333");
        jobSeeker4.setContactEmail("contact4@example.com");
        jobSeeker4.setRoles(Set.of(seekerRole));
        jobSeeker4.setAddress(new Address("10 Downing Street", "London", 26568, "UK"));
        jobSeeker4.setBirthDate(LocalDate.of(1980, 12, 25));
        jobSeeker4.setGender(Gender.M);


        JobSeeker jobSeeker5 = new JobSeeker();
        jobSeeker5.setEmail("seeker5@example.com");
        jobSeeker5.setPassword(passwordEncoder.encode("passwordxyz"));
        jobSeeker5.setFirstname("David");
        jobSeeker5.setLastname("Martinez");
        jobSeeker5.setPhoneNumber("999888777");
        jobSeeker5.setContactEmail("contact5@example.com");
        jobSeeker5.setRoles(Set.of(seekerRole));
        jobSeeker5.setAddress(new Address(
                "Kremlin Embankment",
                "Moscow",
                103073,
                "Russia")
        );
        jobSeeker5.setBirthDate(LocalDate.of(1999, 6, 15));
        jobSeeker5.setGender(Gender.X);

        List<JobSeeker> jobSeekers = List.of(jobSeeker1, jobSeeker2, jobSeeker3, jobSeeker4, jobSeeker5);

        jobSeekerRepository.saveAll(jobSeekers);
    }
}
