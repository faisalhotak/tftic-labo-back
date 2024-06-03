package be.portal.job.services;

import be.portal.job.dtos.application.requests.ApplicationRequest;
import be.portal.job.dtos.application.requests.ApplicationUpdateRequest;
import be.portal.job.dtos.application.responses.ApplicationResponse;

import java.util.List;

public interface IApplicationService {

    /**
     * Retrieves a list of all the seeker's applications.
     * @return a List of Application objects representing all applications.
     */
    List<ApplicationResponse> getAllBySeeker();

    /**
     * Retrieves an application by its unique identifier.
     * @param id the unique identifier of the application.
     * @return the Application object corresponding to the specified id.
     */
    ApplicationResponse getApplicationById(Long id);

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
}
