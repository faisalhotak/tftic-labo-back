package be.portal.job.dtos.skill_set.responses;

import be.portal.job.dtos.skill_detail.responses.SkillDetailResponse;
import be.portal.job.entities.SkillSet;
import be.portal.job.enums.SkillLevel;

public record SkillSetResponse(

        Long id,
        SkillLevel skillLevel,
        int years,
        SkillDetailResponse skillDetailId
) {
    public static SkillSetResponse fromEntity(SkillSet skillSet) {
        return new SkillSetResponse(
                skillSet.getId(),
                skillSet.getSkillLevel(),
                skillSet.getYears(),
                SkillDetailResponse.fromEntity(skillSet.getSkillDetail())
        );
    }
}
