package be.portal.job.dtos.skill_set.requests;

import be.portal.job.entities.JobSeeker;
import be.portal.job.entities.SkillDetail;
import be.portal.job.entities.SkillSet;
import be.portal.job.enums.SkillLevel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SkillSetAddRequest(

        @NotNull(message = "Skill level cannot be null")
        SkillLevel skillLevel,

        @Min(value = 0)
        int years
) {
    public SkillSet toEntity(JobSeeker jobSeeker, SkillDetail skillDetail) {
        return new SkillSet(
                skillLevel,
                years,
                jobSeeker,
                skillDetail
        );
    }
}
