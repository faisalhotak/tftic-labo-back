package be.portal.job.services;

import be.portal.job.dtos.job_function.requests.JobFunctionRequest;
import be.portal.job.dtos.job_function.responses.JobFunctionResponse;

import java.util.List;

/**
 * This interface defines methods for managing job functions.
 */
public interface IJobFunctionService {

    /**
     * Get all job functions.
     * @return list of job functions responses
     */
    List<JobFunctionResponse> getAll();

    /**
     * Get job function by id.
     * @param id job function id
     * @return job function response
     */
    JobFunctionResponse getById(Long id);

    /**
     * Add new job function based on request.
     * @param request job function request
     * @return job function response
     */
    JobFunctionResponse add(JobFunctionRequest request);

    /**
     * Update job function based on request.
     * @param id job function id
     * @param request job function request
     * @return job function response
     */
    JobFunctionResponse update(Long id, JobFunctionRequest request);

    /**
     * Delete job function by id.
     * @param id job function id
     * @return job function response
     */
    JobFunctionResponse delete(Long id);
}
