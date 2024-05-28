package be.portal.job.dtos.social.requests;

import be.portal.job.entities.Social;
import jakarta.validation.constraints.NotBlank;

public record SocialRequest(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Logo URL is required")
        String logoUrl
) {
    public Social toEntity() {

        Social social = new Social();

        social.setName(name);
        social.setLogoUrl(logoUrl);

        return social;
    }
}
