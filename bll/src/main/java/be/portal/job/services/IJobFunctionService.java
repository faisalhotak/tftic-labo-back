package be.portal.job.services;

import be.portal.job.entities.JobFunction;

import java.util.List;
/**
 * This interface defines methods for managing job functions.
 */
public interface IJobFunctionService {

    /**
     * Retrieves a list of all job functions.
     * @return A list of all job functions.
     */
    List<JobFunction> getJobFunction();

    /**
     * Retrieves a job function by its name.
     * @param name The name of the job function to retrieve.
     * @return The job function with the specified name, or null if not found.
     */
    JobFunction getJobFunctionByName(String name);

    /**
     * Retrieves a job function by its ID.
     * @param id The ID of the job function to retrieve.
     * @return The job function with the specified ID, or null if not found.
     */
    JobFunction getJobFunctionById(Long id);

    /**
     * Creates a new job function.
     * @param jobFunction The job function to create.
     * @return The newly created job function.
     */
    JobFunction addJobFunction(JobFunction jobFunction);

    /**
     * Updates an existing job function.
     * @param id The ID of the job function to update.
     * @param jobFunction The updated job function object.
     * @return The updated job function.
     */
    JobFunction updateJobFunction(Long id, JobFunction jobFunction);

    /**
     * Deletes a job function by its ID.
     * @param id The ID of the job function to delete.
     */
    void deleteJobFunction(Long id);
}
