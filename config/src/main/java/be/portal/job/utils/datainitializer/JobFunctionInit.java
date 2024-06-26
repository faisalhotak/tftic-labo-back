package be.portal.job.utils.datainitializer;

import be.portal.job.entities.JobFunction;
import be.portal.job.repositories.JobFunctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(7)
public class JobFunctionInit implements CommandLineRunner {

    private final JobFunctionRepository jobFunctionRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create a few job functions with arbitrary values
        List<JobFunction> jobFunctions = List.of(
                new JobFunction("Software Developer"),
                new JobFunction("Marketing Manager"),
                new JobFunction("Human Resources Specialist")
        );

        jobFunctionRepository.saveAll(jobFunctions);
    }
}
