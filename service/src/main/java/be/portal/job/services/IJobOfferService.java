package be.portal.job.services;

import be.portal.job.dtos.job_offer.requests.JobOfferRequest;
import be.portal.job.dtos.job_offer.requests.JobOfferTransferRequest;
import be.portal.job.dtos.job_offer.responses.JobOfferResponse;
import be.portal.job.dtos.job_offer.responses.PagedJobOfferResponse;

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
     * @param page the page number to retrieve.
     * @return a paged response containing the job offers.
     */
    PagedJobOfferResponse getAll(Map<String, String> params, int page);

    /**
     * Retrieves a list of all job offers by agent.
     * @param id the identifier of the agent.
     * @return a list of all job offers by the specified agent.
     */
    List<JobOfferResponse> getAllByAgent(Long id);

    /**
     * Retrieves a job offer by its unique identifier.
     * @param id the identifier of the job offer to search for.
     * @return the job offer corresponding to the provided identifier, or null if not found.
     */
    JobOfferResponse getJobOfferById(Long id);

    /**
     * Retrieves all job offers for a specified company.
     * @param id the identifier of the company.
     * @return a list of job offers for the specified company.
     */
    List<JobOfferResponse> getAllJobOffersByCompany(Long id);

    /**
     * Retrieves all the locations where there is a jobOffer.
     * @return a list of Locations
     */
    List<String> getAllLocations();

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

    /**
     * Transfers a job offer to another company advertiser who is a colleague of the same company.
     * @param id the identifier of the job offer to transfer.
     * @param jobOfferTransferRequest the request containing the new advertiser's information.
     * @return the transferred job offer.
     */
    JobOfferResponse transferJobOffer(Long id, JobOfferTransferRequest jobOfferTransferRequest);

    /**
     * Deletes a job offer by its identifier.
     * @param id the identifier of the job offer to delete.
     * @return the deleted job offer, or null if the job offer does not exist.
     */
    JobOfferResponse deleteJobOffer(Long id);

    /**
     * Triggers the active status of a job offer.
     * @param id the identifier of the job offer to trigger.
     * @param isActive the new active status of the job offer.
     * @return the updated job offer.
     */
    JobOfferResponse triggerActive(Long id, boolean isActive);
}
