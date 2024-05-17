package be.portal.job.utils.dataInitializer;

import be.portal.job.entities.JobAdvertiser;
import be.portal.job.entities.Address;
import be.portal.job.repositories.AddressRepository;
import be.portal.job.entities.Role;
import be.portal.job.repositories.RoleRepository;
import be.portal.job.repositories.JobAdvertiserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(3)
public class JobAdvertiserInit implements CommandLineRunner {

    private final JobAdvertiserRepository jobAdvertiserRepository;
    private final AddressRepository addressRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Role> userRoles = roleRepository.findAll();
        List<Address> addresses = addressRepository.findAll();

        Set<Role> role1 = new HashSet<Role>();
        role1.add(userRoles.get(0));

        // Job Advertiser 1
        JobAdvertiser jobAdvertiser1 = new JobAdvertiser();
        jobAdvertiser1.setEmail("user1@example.com");
        jobAdvertiser1.setPassword("password123");
        jobAdvertiser1.setFirstname("John");
        jobAdvertiser1.setLastname("Doe");
        jobAdvertiser1.setPhoneNumber(123456789);
        jobAdvertiser1.setContactEmail("contact1@example.com");
        jobAdvertiser1.setRoles(role1);
        jobAdvertiser1.setAddress(addresses.get(0));

// Job Advertiser 2
        JobAdvertiser jobAdvertiser2 = new JobAdvertiser();
        jobAdvertiser2.setEmail("user2@example.com");
        jobAdvertiser2.setPassword("password456");
        jobAdvertiser2.setFirstname("Jane");
        jobAdvertiser2.setLastname("Smith");
        jobAdvertiser2.setPhoneNumber(987654321);
        jobAdvertiser2.setContactEmail("contact2@example.com");
        jobAdvertiser2.setRoles(role1);
        jobAdvertiser2.setAddress(addresses.get(1));

// Job Advertiser 3
        JobAdvertiser jobAdvertiser3 = new JobAdvertiser();
        jobAdvertiser3.setEmail("user3@example.com");
        jobAdvertiser3.setPassword("password789");
        jobAdvertiser3.setFirstname("Michael");
        jobAdvertiser3.setLastname("Johnson");
        jobAdvertiser3.setPhoneNumber(555444333);
        jobAdvertiser3.setContactEmail("contact3@example.com");
        jobAdvertiser3.setRoles(role1);
        jobAdvertiser3.setAddress(addresses.get(2));

// Job Advertiser 4
        JobAdvertiser jobAdvertiser4 = new JobAdvertiser();
        jobAdvertiser4.setEmail("user4@example.com");
        jobAdvertiser4.setPassword("passwordabc");
        jobAdvertiser4.setFirstname("Emily");
        jobAdvertiser4.setLastname("Brown");
        jobAdvertiser4.setPhoneNumber(111222333);
        jobAdvertiser4.setContactEmail("contact4@example.com");
        jobAdvertiser4.setRoles(role1);
        jobAdvertiser4.setAddress(addresses.get(3));

// Job Advertiser 5
        JobAdvertiser jobAdvertiser5 = new JobAdvertiser();
        jobAdvertiser5.setEmail("user5@example.com");
        jobAdvertiser5.setPassword("passwordxyz");
        jobAdvertiser5.setFirstname("David");
        jobAdvertiser5.setLastname("Martinez");
        jobAdvertiser5.setPhoneNumber(999888777);
        jobAdvertiser5.setContactEmail("contact5@example.com");
        jobAdvertiser5.setRoles(role1);
        jobAdvertiser5.setAddress(addresses.get(4));


        List<JobAdvertiser> JobAdvertisers = List.of(jobAdvertiser1, jobAdvertiser2, jobAdvertiser3, jobAdvertiser4, jobAdvertiser5);

        jobAdvertiserRepository.saveAll(JobAdvertisers);
    }
}
