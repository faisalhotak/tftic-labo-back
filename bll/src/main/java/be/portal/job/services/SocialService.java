package be.portal.job.services;

import be.portal.job.entities.Social;

import java.util.List;

public interface SocialService {

    /**
     * Retrieves all social network entries currently present in the system.
     * @return A list of Social objects representing the details of each social network.
     */
    List<Social> getAllSocial();

    /**
     * Retrieves a specific social network entry from the system based on its ID.
     * @param id The ID of the social network to be retrieved.
     * @return The Social object containing the details of the requested social network, if found.
     */
    Social getSocialById(Long id);

    /**
     * Creates a new social network entry in the system.
     * @param social The details of the social network to be created.
     */
    Social addSocial(Social social);

    /**
     * Updates an existing social network entry in the system.
     * @param id     The ID of the social network to be updated.
     * @param social The updated details of the social network.
     */
    Social updateSocial(Long id, Social social);

    /**
     * Deletes a social network entry from the system.
     * @param id The ID of the social network to be deleted.
     */
    void deleteSocial(Long id);
}
