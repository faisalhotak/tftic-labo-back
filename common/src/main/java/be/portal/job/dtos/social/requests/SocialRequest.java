package be.portal.job.dtos.social.requests;

import jakarta.validation.constraints.NotBlank;

public record SocialRequest(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Logo URL is required")
        String logoUrl
) { }
