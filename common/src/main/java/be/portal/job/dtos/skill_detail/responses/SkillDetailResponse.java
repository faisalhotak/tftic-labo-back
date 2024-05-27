package be.portal.job.dtos.skill_detail.responses;

import be.portal.job.entities.SkillDetail;

public record SkillDetailResponse(

        Long id,
        String name
) {
    public static SkillDetailResponse fromEntity(SkillDetail skillDetail) {
        return new SkillDetailResponse(
                skillDetail.getId(),
                skillDetail.getName()
        );
    }
}
