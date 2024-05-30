package be.portal.job.mappers.social_link;

import be.portal.job.dtos.social_link.requests.SocialLinkRequest;
import be.portal.job.dtos.social_link.responses.SocialLinkResponse;
import be.portal.job.entities.Social;
import be.portal.job.entities.SocialLink;
import be.portal.job.entities.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SocialLinkMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "social", target = "social")
    @Mapping(source = "user", target = "user")
    SocialLink toEntity(SocialLinkRequest request, Social social, User user);

    SocialLinkResponse fromEntity(SocialLink entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "social", target = "social")
    void updateEntityFromRequest(SocialLinkRequest request, Social social, @MappingTarget SocialLink socialLink);
}
