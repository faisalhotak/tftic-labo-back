package be.portal.job.dtos.skill_set.responses;

import be.portal.job.dtos.skill_detail.responses.SkillDetailResponse;
import be.portal.job.dtos.user.responses.UserShortResponse;
import be.portal.job.enums.SkillLevel;

public record SkillSetResponse(

        Long id,
        SkillLevel skillLevel,
        int years,
        SkillDetailResponse skillDetail,
        UserShortResponse jobSeeker
) { }
