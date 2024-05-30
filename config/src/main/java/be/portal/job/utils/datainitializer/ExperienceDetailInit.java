package be.portal.job.utils.datainitializer;

import be.portal.job.entities.ExperienceDetail;
import be.portal.job.entities.JobSeeker;
import be.portal.job.repositories.ExperienceDetailRepository;
import be.portal.job.repositories.JobSeekerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(12)
public class ExperienceDetailInit implements CommandLineRunner {

    private final ExperienceDetailRepository experienceDetailRepository;
    private final JobSeekerRepository jobSeekerRepository;

    @Override
    public void run(String... args) throws Exception {

        List<JobSeeker> jobSeekers = jobSeekerRepository.findAll();
        List<ExperienceDetail> experienceDetails = List.of(
                new ExperienceDetail( "Société A", "Développement d'applications Java", LocalDate.parse("2020-01-01"), LocalDate.parse("2021-01-01"), jobSeekers.get(0)),
                new ExperienceDetail( "Société B", "Développement d'applications JS", LocalDate.parse("2019-01-01"), LocalDate.parse("2020-01-01"), jobSeekers.get(0)),
                new ExperienceDetail( "Société C", "Développement d'applications Python", LocalDate.parse("2018-01-01"), LocalDate.parse("2019-01-01"), jobSeekers.get(1))
        );

        experienceDetailRepository.saveAll(experienceDetails);
    }
}