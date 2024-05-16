package be.portal.job.services;

import be.portal.job.entities.SkillDetail;
import java.util.List;

public interface SkillDetailService {

    /**
     * Retrieves all skill details from the system.
     * @return A list of SkillDetail objects representing all skill details.
     */
    List<SkillDetail> getAllSkillDetail();

    /**
     * Retrieves the skill detail associated with a specific skill ID.
     * @param id The ID of the skill.
     * @return The SkillDetail object representing the details of the specified skill.
     */
    SkillDetail getSkillDetailById(Long id);

    /**
     * Adds new skill detail to the system.
     * @param skillDetail The SkillDetail object containing the details of the skill to be added.
     * @return The SkillDetail object representing the newly added skill detail.
     */
    SkillDetail addSkillDetail(SkillDetail skillDetail);

    /**
     * Updates existing skill detail in the system.
     * @param id           The ID of the skill detail to be updated.
     * @param skillDetail The SkillDetail object containing the updated details of the skill.
     * @return The SkillDetail object representing the updated skill detail.
     */
    SkillDetail updateSkillDetail(Long id, SkillDetail skillDetail);

    /**
     * Deletes skill detail from the system based on the specified ID.
     * @param id The ID of the skill detail to be deleted.
     */
    void deleteSkillDetail(Long id);
}
