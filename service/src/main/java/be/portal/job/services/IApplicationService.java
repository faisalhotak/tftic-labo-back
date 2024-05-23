package be.portal.job.services;

import be.portal.job.dtos.application.requests.ApplicationRequest;
import be.portal.job.dtos.application.responses.ApplicationResponse;
import be.portal.job.entities.Application;

import java.util.List;

public interface IApplicationService {

    /**
     * Retrieves a list of all applications.
     * @return a List of Application objects representing all applications.
     */
    List<ApplicationResponse> getAll();

    /**
     * Retrieves an application by its unique identifier.
     * @param id the unique identifier of the application.
     * @return the Application object corresponding to the specified id.
     */
    ApplicationResponse getApplicationById(Long id);

    /**
     * Adds a new application.
     * @param applicationRequest the Application object to be added.
     * @return the added Application object.
     */
    ApplicationResponse addApplication(ApplicationRequest applicationRequest);

    /**
     * Updates an existing application.
     * @param id the unique identifier of the application to be updated.
     * @param applicationRequest the Application object containing the updated information.
     * @return the updated Application object.
     */
    ApplicationResponse updateApplication(Long id, ApplicationRequest applicationRequest);

    /**
     * Deletes an application by its unique identifier.
     * @param id the unique identifier of the application to be deleted.
     */
    ApplicationResponse deleteApplication(Long id);
}
