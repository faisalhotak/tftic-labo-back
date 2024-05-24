package be.portal.job.utils.datainitializer;

import be.portal.job.entities.Application;
import be.portal.job.entities.JobOffer;
import be.portal.job.entities.JobSeeker;
import be.portal.job.enums.ApplicationStatus;
import be.portal.job.repositories.ApplicationRepository;
import be.portal.job.repositories.JobOfferRepository;
import be.portal.job.repositories.JobSeekerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Order(11)
public class ApplicationInit implements CommandLineRunner {

    private final ApplicationRepository applicationRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final JobOfferRepository jobOfferRepository;
    private final Random rand = new Random();

    @Override
    public void run(String... args) throws Exception {
        List<JobSeeker> jobSeekers = jobSeekerRepository.findAll();
        List<JobOffer> jobOffers = jobOfferRepository.findAll();
        List<ApplicationStatus> applicationStatuses = List.of(ApplicationStatus.values());
        List<Application> applications = new ArrayList<>();

        jobOffers.forEach(jobOffer ->
                jobSeekers.forEach(jobSeeker -> {
                    Application application = new Application(
                            LocalDateTime.now(),
                            applicationStatuses.get(rand.nextInt(applicationStatuses.size())),
                            jobSeeker,
                            jobOffer
                    );

                    applications.add(application);
                })
        );

        applicationRepository.saveAll(applications);
    }
}
