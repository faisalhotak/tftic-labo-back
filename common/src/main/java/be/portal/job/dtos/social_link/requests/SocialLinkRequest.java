package be.portal.job.dtos.social_link.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SocialLinkRequest(

        @NotBlank(message = "There must be a url")
        String url,

        @NotNull(message = "There must be a social id")
        Long socialId
) { }
