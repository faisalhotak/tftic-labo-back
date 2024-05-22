package be.portal.job.dtos.social_link.requests;

import be.portal.job.entities.Social;
import be.portal.job.entities.SocialLink;
import be.portal.job.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SocialLinkRequest(

        @NotBlank(message = "There must be a url")
        String url,

        @NotNull(message = "There must be a social id")
        Long socialId
) {
    public SocialLink toEntity(Social social, User user) {
        return new SocialLink(url(), social, user);
    }
}
