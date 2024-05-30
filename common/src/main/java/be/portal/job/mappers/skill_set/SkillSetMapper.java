package be.portal.job.mappers.skill_set;

import be.portal.job.dtos.skill_set.requests.SkillSetRequest;
import be.portal.job.dtos.skill_set.responses.SkillSetResponse;
import be.portal.job.entities.JobSeeker;
import be.portal.job.entities.SkillDetail;
import be.portal.job.entities.SkillSet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SkillSetMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "jobSeeker", target = "jobSeeker")
    @Mapping(source = "skillDetail", target = "skillDetail")
    SkillSet toEntity(SkillSetRequest request, JobSeeker jobSeeker, SkillDetail skillDetail);

    SkillSetResponse fromEntity(SkillSet entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "jobSeeker", ignore = true)
    @Mapping(source = "skillDetail", target = "skillDetail")
    void updateEntityFromRequest(SkillSetRequest request, SkillDetail skillDetail, @MappingTarget SkillSet skillSet);
}
