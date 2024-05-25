package be.portal.job.dtos.social_link.responses;

import be.portal.job.dtos.social.responses.SocialResponse;

public record SocialLinkResponse(
        String url,
        SocialResponse social
) { }
