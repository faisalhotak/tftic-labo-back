package be.portal.job.dtos.skill_detail.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SkillDetailRequest(

        @NotBlank(message = "You must provide a name for the skill detail.")
        @Size(min = 1, max = 100)
        String name
) { }
