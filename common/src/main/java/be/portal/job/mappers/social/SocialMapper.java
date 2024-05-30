package be.portal.job.mappers.social;

import be.portal.job.dtos.social.requests.SocialRequest;
import be.portal.job.dtos.social.responses.SocialResponse;
import be.portal.job.entities.Social;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SocialMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Social toEntity(SocialRequest request);

    SocialResponse fromEntity(Social entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromRequest(SocialRequest request, @MappingTarget Social social);
}
