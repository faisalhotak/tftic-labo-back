package be.portal.job.services.impls;

import be.portal.job.dtos.job_function.requests.JobFunctionRequest;
import be.portal.job.dtos.job_function.responses.JobFunctionResponse;
import be.portal.job.entities.JobFunction;
import be.portal.job.exceptions.job_function.JobFunctionAlreadyExistsException;
import be.portal.job.exceptions.job_function.JobFunctionNotFoundException;
import be.portal.job.mappers.job_function.JobFunctionMapper;
import be.portal.job.repositories.JobFunctionRepository;
import be.portal.job.services.IJobFunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobFunctionServiceImpl implements IJobFunctionService {

    private final JobFunctionRepository jobFunctionRepository;
    private final JobFunctionMapper jobFunctionMapper;

    @Override
    public List<JobFunctionResponse> getAll() {
        return jobFunctionRepository.findAll().stream()
                .map(jobFunctionMapper::fromEntity)
                .toList();
    }

    @Override
    public JobFunctionResponse getById(Long id) {
        JobFunction jobFunction = jobFunctionRepository.findById(id).orElseThrow(JobFunctionNotFoundException::new);

        return jobFunctionMapper.fromEntity(jobFunction);
    }

    @Override
    public JobFunctionResponse add(JobFunctionRequest request) {
        if (jobFunctionRepository.findByName(request.name()).isPresent()) {
            throw new JobFunctionAlreadyExistsException();
        }

        JobFunction jobFunction = jobFunctionMapper.toEntity(request);

        return jobFunctionMapper.fromEntity(jobFunctionRepository.save(jobFunction));
    }

    @Override
    public JobFunctionResponse update(Long id, JobFunctionRequest request) {
        JobFunction existingJobFunction = jobFunctionRepository.findById(id).orElseThrow(JobFunctionNotFoundException::new);

        if (jobFunctionRepository.findByName(request.name()).isPresent()) {
            throw new JobFunctionAlreadyExistsException();
        }

        jobFunctionMapper.updateEntityFromRequest(request, existingJobFunction);

        return jobFunctionMapper.fromEntity(jobFunctionRepository.save(existingJobFunction));
    }

    @Override
    public JobFunctionResponse delete(Long id) {
        JobFunction jobFunction = jobFunctionRepository.findById(id).orElseThrow(JobFunctionNotFoundException::new);

        jobFunctionRepository.delete(jobFunction);

        return jobFunctionMapper.fromEntity(jobFunction);
    }
}
