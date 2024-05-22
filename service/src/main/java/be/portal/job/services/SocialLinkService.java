package be.portal.job.services;


import be.portal.job.dtos.social_link.requests.SocialLinkRequest;
import be.portal.job.dtos.social_link.responses.SocialLinkResponse;

import java.util.List;

public interface SocialLinkService {

    /**
     * Gets all social links.
     * @return A list of all social links.
     */
    List<SocialLinkResponse> getAll();

    /**
     * Gets a social link by its ID.
     * @param id The ID of the social link to be retrieved.
     * @return The social link with the given ID.
     */
    SocialLinkResponse getById(Long id);

    /**
     * Adds a social link.
     * @param request The request containing the data of the social link to be added.
     * @return The added social link.
     */
    SocialLinkResponse add(SocialLinkRequest request);

    /**
     * Updates a social link.
     * @param id The ID of the social link to be updated.
     * @param request The request containing the updated data of the social link.
     * @return The updated social link.
     */
    SocialLinkResponse update(Long id, SocialLinkRequest request);

    /**
     * Deletes a social link.
     * @param id The ID of the social link to be deleted.
     * @return The deleted social link.
     */
    SocialLinkResponse delete(Long id);
}
