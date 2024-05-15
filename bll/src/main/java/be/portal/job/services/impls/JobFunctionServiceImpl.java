package be.portal.job.services.impls;

import be.portal.job.entities.JobFunction;
import be.portal.job.repositories.JobFunctionRepository;
import be.portal.job.services.IJobFunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobFunctionServiceImpl implements IJobFunctionService {

    private final JobFunctionRepository jobFunctionRepository;

    @Override
    public List<JobFunction> getJobFunction() {
        return jobFunctionRepository.findAll();
    }

    @Override
    public JobFunction getJobFunctionByName(String name) {
        return jobFunctionRepository.findByName(name);
    }

    @Override
    public JobFunction getJobFunctionById(Long id) {
        return jobFunctionRepository.findById(id).orElseThrow();
    }

    @Override
    public JobFunction addJobFunction(JobFunction jobFunction) {
        return jobFunctionRepository.save(jobFunction);
    }

    @Override
    public JobFunction updateJobFunction(Long id, JobFunction jobFunction) {
        JobFunction existingJobFunction = jobFunctionRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Job function not found: " + id));

        existingJobFunction.setName(jobFunction.getName());
        existingJobFunction.setTitle(jobFunction.getTitle());

        return jobFunctionRepository.save(existingJobFunction);
    }

    @Override
    public void deleteJobFunction(Long id) {
        if (!jobFunctionRepository.existsById(id)) {
            throw new RuntimeException("Job function not found: " + id);
        }
        jobFunctionRepository.deleteById(id);
    }
}
