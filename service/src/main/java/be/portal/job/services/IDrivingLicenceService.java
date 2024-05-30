package be.portal.job.services;

import be.portal.job.dtos.driving_licence.requests.DrivingLicenceAddRequest;
import be.portal.job.dtos.driving_licence.requests.DrivingLicenceUpdateRequest;
import be.portal.job.dtos.driving_licence.responses.DrivingLicenceResponse;

import java.util.List;

/**
 * Interface for managing driving licence operations.
 */
public interface IDrivingLicenceService {

    /**
     * Retrieves all driving licences associated with a seeker.
     * @return a list of {@link DrivingLicenceResponse} objects.
     */
    List<DrivingLicenceResponse> getAllBySeeker();

    /**
     * Retrieves a driving licence by its ID.
     * @param id the ID of the driving licence.
     * @return the {@link DrivingLicenceResponse} object.
     */
    DrivingLicenceResponse getById(Long id);

    /**
     * Adds a new driving licence.
     * @param requestAdd the request object containing details of the driving licence to add.
     * @return the added {@link DrivingLicenceResponse} object.
     */
    DrivingLicenceResponse add(DrivingLicenceAddRequest requestAdd);

    /**
     * Updates an existing driving licence.
     * @param id the ID of the driving licence to update.
     * @param requestUpdate the request object containing updated details of the driving licence.
     * @return the updated {@link DrivingLicenceResponse} object.
     */
    DrivingLicenceResponse update(Long id, DrivingLicenceUpdateRequest requestUpdate);

    /**
     * Deletes a driving licence by its ID.
     * @param id the ID of the driving licence to delete.
     * @return the deleted {@link DrivingLicenceResponse} object.
     */
    DrivingLicenceResponse delete(Long id);
}
