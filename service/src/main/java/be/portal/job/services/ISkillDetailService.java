package be.portal.job.services;

import be.portal.job.dtos.skill_detail.requests.SkillDetailRequest;
import be.portal.job.dtos.skill_detail.responses.SkillDetailResponse;
import java.util.List;

public interface ISkillDetailService {

    /**
     * Retrieves a list of all skill details.
     * @return a list of skill detail responses.
     */
    List<SkillDetailResponse> getAll();

    /**
     * Retrieves a skill detail by its ID.
     * @param id the ID of the skill detail.
     * @return the skill detail response.
     */
    SkillDetailResponse getSkillDetailById(Long id);

    /**
     * Adds a new skill detail.
     * @param skillDetail the request containing the data for the new skill detail.
     * @return the response of the added skill detail.
     */
    SkillDetailResponse addSkillDetail(SkillDetailRequest skillDetail);

    /**
     * Updates an existing skill detail.
     * @param id the ID of the skill detail to be updated.
     * @param skillDetail the request containing the new data for the skill detail.
     * @return the response of the updated skill detail.
     */
    SkillDetailResponse updateSkillDetail(Long id, SkillDetailRequest skillDetail);

    /**
     * Deletes a skill detail by its ID.
     * @param id the ID of the skill detail to be deleted.
     * @return the response of the deleted skill detail.
     */
    SkillDetailResponse deleteSkillDetail(Long id);
}
