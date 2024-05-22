package be.portal.job.services;

import be.portal.job.entities.Application;

import java.util.List;

public interface IApplicationService {

    /**
     * Retrieves a list of all applications.
     * @return a List of Application objects representing all applications.
     */
    List<Application> getApplications();

    /**
     * Retrieves an application by its unique identifier.
     * @param id the unique identifier of the application.
     * @return the Application object corresponding to the specified id.
     */
    Application getApplication(Long id);

    /**
     * Adds a new application.
     * @param application the Application object to be added.
     * @return the added Application object.
     */
    Application addApplication(Application application);

    /**
     * Updates an existing application.
     * @param id the unique identifier of the application to be updated.
     * @param application the Application object containing the updated information.
     * @return the updated Application object.
     */
    Application updateApplication(Long id, Application application);

    /**
     * Deletes an application by its unique identifier.
     * @param id the unique identifier of the application to be deleted.
     */
    void deleteApplication(Long id);
}
