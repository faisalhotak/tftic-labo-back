package be.portal.job.mappers.experience_detail;

import be.portal.job.dtos.experience_detail.requests.ExperienceDetailAddRequest;
import be.portal.job.dtos.experience_detail.responses.ExperienceDetailResponse;
import be.portal.job.entities.ExperienceDetail;
import be.portal.job.entities.JobSeeker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExperienceDetailMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "jobSeeker", source = "jobSeeker")
    ExperienceDetail toEntity(ExperienceDetailAddRequest request, JobSeeker jobSeeker);

    ExperienceDetailResponse fromEntity(ExperienceDetail entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "jobSeeker", ignore = true)
    void updateEntityFromRequest(ExperienceDetailAddRequest request, @MappingTarget ExperienceDetail experienceDetail);
}
