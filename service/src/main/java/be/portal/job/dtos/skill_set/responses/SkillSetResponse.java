package be.portal.job.dtos.skill_set.responses;

import be.portal.job.entities.SkillSet;
import be.portal.job.enums.SkillLevel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SkillSetResponse(

        @NotNull(message = "Skill level cannot be null")
        SkillLevel skillLevel,

        @Min(value = 0)
        int years,

        @NotNull
        Long skillDetailId
) {
    public SkillSetResponse fromEntity(SkillSet skillSet) {
        return new SkillSetResponse(
                skillSet.getSkillLevel(),
                skillSet.getYears(),
                skillSet.getSkillDetail().getId()
        );
    }
}
