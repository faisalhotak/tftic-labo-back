package be.portal.job.services;

import be.portal.job.dtos.skill_set.requests.SkillSetRequest;
import be.portal.job.dtos.skill_set.responses.SkillSetResponse;

import java.util.List;

public interface ISkillSetService {

    /**
     * Retrieves all skill sets associated with the authenticated job seeker.
     * @return a list of SkillSetResponse objects representing all skill sets of the authenticated job seeker.
     */
    List<SkillSetResponse> getAllBySeeker();

    /**
     * Retrieves a specific skill set by its ID.
     * @param id the ID of the skill set to be retrieved.
     * @return a SkillSetResponse object representing the skill set with the specified ID.
     */
    SkillSetResponse getById(Long id);

    /**
     * Adds a new skill set for the authenticated job seeker.
     * @param request the SkillSetRequest object containing the details of the skill set to be added.
     * @return a SkillSetResponse object representing the added skill set.
     */
    SkillSetResponse add(SkillSetRequest request);

    /**
     * Updates an existing skill set for the authenticated job seeker.
     * @param id the ID of the skill set to be updated.
     * @param request the SkillSetUpdateRequest object containing the new details of the skill set.
     * @return a SkillSetResponse object representing the updated skill set.
     */
    SkillSetResponse update(Long id, SkillSetRequest request);

    /**
     * Deletes a specific skill set by its ID.
     * @param id the ID of the skill set to be deleted.
     * @return a SkillSetResponse object representing the deleted skill set.
     */
    SkillSetResponse delete(Long id);
}
