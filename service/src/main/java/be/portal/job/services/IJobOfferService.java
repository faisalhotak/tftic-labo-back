package be.portal.job.services;

import be.portal.job.dtos.job_offer.requests.JobOfferRequest;
import be.portal.job.dtos.job_offer.responses.JobOfferResponse;

import java.util.List;
import java.util.Map;

/**
 * Service interface for job offer management in the system.
 * Provides basic CRUD operations for job offers.
 */
public interface IJobOfferService {

    /**
     * Retrieves a list of all job offers.
     * @param params the parameters to filter the job offers.
     * @return a list of all job offers.
     */
    List<JobOfferResponse> getAll(Map<String, String> params);

    /**
     * Retrieves a list of all job offers by agent.
     * @param id the identifier of the agent.
     * @return a list of all job offers by the specified agent.
     */
    List<JobOfferResponse> getAllByAgent(Long id);

    /**
     * Retrieves a job offer by its unique identifier.
     *
     * @param id the identifier of the job offer to search for.
     * @return the job offer corresponding to the provided identifier, or null if not found.
     */
    JobOfferResponse getJobOfferById(Long id);

    /**
     * Deletes a job offer by its identifier.
     * @param id the identifier of the job offer to delete.
     * @return the deleted job offer, or null if the job offer does not exist.
     */
    JobOfferResponse deleteJobOffer(Long id);

    /**
     * Adds a new job offer to the system.
     * @param jobOfferRequest the job offer to add.
     * @return the added job offer with its generated identifier.
     */
    JobOfferResponse addJobOffer(JobOfferRequest jobOfferRequest);

    /**
     * Updates an existing job offer.
     * @param id the identifier of the job offer to update.
     * @param jobOfferRequest the new information for the job offer.
     * @return the updated job offer, or null if the job offer does not exist.
     */
    JobOfferResponse updateJobOffer(Long id, JobOfferRequest jobOfferRequest);

}