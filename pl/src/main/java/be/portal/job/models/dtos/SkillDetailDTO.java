package be.portal.job.models.dtos;

import be.portal.job.entities.SkillDetail;

public record SkillDetailDTO(

        Long id,
        String name
) {
    public static SkillDetailDTO fromEntity(SkillDetail skillDetail) {
        return new SkillDetailDTO(
                skillDetail.getId(),
                skillDetail.getName()
        );
    }
}
