package be.portal.job.models.forms;

import be.portal.job.entities.Social;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SocialForm(

        @NotBlank(message = "Social Network name")
        @Size(min = 1, max = 100)
        String name,

        @NotBlank(message = "Logo url")
        @Size(min = 1, max = 255)
        String logoUrl
) {
    public Social toEntity() {
        Social social = new Social();

        social.setName(name);
        social.setLogoUrl(logoUrl);

        return social;
    }
}
