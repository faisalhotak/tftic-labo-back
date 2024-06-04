package be.portal.job.dtos.skill_set.requests;

import be.portal.job.enums.SkillLevel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SkillSetRequest(

        @NotNull(message = "Skill level cannot be null")
        SkillLevel skillLevel,

        @Min(value = 0)
        int years,

        @NotNull(message = "Skill detail ID cannot be null")
        Long skillDetailId
) { }
