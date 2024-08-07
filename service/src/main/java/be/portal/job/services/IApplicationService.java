package be.portal.job.services;

import be.portal.job.dtos.application.requests.ApplicationRequest;
import be.portal.job.dtos.application.requests.ApplicationStatusRequest;
import be.portal.job.dtos.application.requests.ApplicationUpdateRequest;
import be.portal.job.dtos.application.responses.ApplicationResponse;
import be.portal.job.dtos.application.responses.PagedApplicationsResponse;

import java.util.List;
import java.util.Map;

public interface IApplicationService {

    /**
     * Retrieves a list of all applications.
     * @param params the parameters to filter the applications.
     * @param page the page number to retrieve.
     * @return a List of Application objects representing all applications.
     */
    PagedApplicationsResponse getAll(Map<String, String> params, int page);

    /**
     * Retrieves a list of all applications by seeker.
     * @param params the parameters to filter the applications.
     * @param page the page number to retrieve.
     * @return a List of Application objects representing all applications.
     */
    PagedApplicationsResponse getAllBySeeker(Map<String, String> params, int page);

    /**
     * Retrieves a list of all applications by job offer.
     * @param id the unique identifier of the job offer.
     * @return a List of Application objects representing all applications.
     */
    List<ApplicationResponse> getAllByJobOfferId(Long id);

    /**
     * Retrieves an application by its unique identifier.
     * @param id the unique identifier of the application.
     * @return the Application object corresponding to the specified id.
     */
    ApplicationResponse getApplicationById(Long id);

    /**
     * Retrieves an application by its unique identifier and the seeker's id.
     * @param id the unique identifier of the application.
     * @return the Application object corresponding to the specified id.
     */
    ApplicationResponse getApplicationByIdAndJobSeekerId(Long id);

    /**
     * Adds a new application.
     * @param request the Application object to be added.
     * @return the added Application object.
     */
    ApplicationResponse addApplication(ApplicationRequest request);

    /**
     * Updates an existing application.
     * @param id the unique identifier of the application to be updated.
     * @param request the Application object containing the updated information.
     * @return the updated Application object.
     */
    ApplicationResponse updateApplication(Long id, ApplicationUpdateRequest request);

    /**
     * Deletes an application by its unique identifier.
     * @param id the unique identifier of the application to be deleted.
     */
    ApplicationResponse deleteApplication(Long id);

    /**
     * Cancels an application by its unique identifier.
     * @param id the unique identifier of the application to be cancelled.
     */
    ApplicationResponse cancelApplication(Long id);

    /**
     * Triggers the status of an application.
     * @param id the unique identifier of the application.
     * @param request the ApplicationStatus object containing the new status.
     * @return the updated Application object.
     */
    ApplicationResponse triggerApplicationStatus(Long id, ApplicationStatusRequest request);
}
