package be.portal.job.dtos.social_link.responses;

import be.portal.job.dtos.social.responses.SocialResponse;
import be.portal.job.entities.SocialLink;

public record SocialLinkResponse(

        String url,
        SocialResponse social
) {
    public static SocialLinkResponse fromEntity(SocialLink socialLink) {
        return new SocialLinkResponse(
                socialLink.getUrl(),
                SocialResponse.fromEntity(socialLink.getSocial())
        );
    }
}
