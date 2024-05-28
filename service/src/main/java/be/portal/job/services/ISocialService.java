package be.portal.job.services;

import be.portal.job.dtos.social.requests.SocialRequest;
import be.portal.job.dtos.social.responses.SocialResponse;

import java.util.List;

public interface ISocialService {

    /**
     * Retrieves a list of all social profiles.
     * @return a list of social profile responses.
     */
    List<SocialResponse> getAll();

    /**
     * Retrieves a social profile by its ID.
     * @param id the ID of the social profile.
     * @return the social profile response.
     */
    SocialResponse getSocialById(Long id);

    /**
     * Adds a new social profile.
     * @param social the request containing the data for the new social profile.
     * @return the response of the added social profile.
     */
    SocialResponse addSocial(SocialRequest social);

    /**
     * Updates an existing social profile.
     * @param id the ID of the social profile to be updated.
     * @param social the request containing the new data for the social profile.
     * @return the response of the updated social profile.
     */
    SocialResponse updateSocial(Long id, SocialRequest social);

    /**
     * Deletes a social profile by its ID.
     * @param id the ID of the social profile to be deleted.
     * @return the response of the deleted social profile.
     */
    SocialResponse deleteSocial(Long id);
}
