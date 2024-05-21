package be.portal.job.models.forms;

import be.portal.job.entities.SkillDetail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SkillDetailForm(

        @NotBlank(message = "Skill name")
        @Size(min = 1, max = 100)
        String name
) {
    public SkillDetail toEntity() {
        SkillDetail skillDetail = new SkillDetail();

        skillDetail.setName(name);

        return skillDetail;
    }
}