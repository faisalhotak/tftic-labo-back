package be.portal.job.dtos.social.responses;

import be.portal.job.entities.Social;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SocialResponse(

        @NotBlank(message = "Social Network name")
        @Size(min = 1, max = 100)
        String name,

        @NotBlank(message = "Logo url")
        @Size(min = 1, max = 255)
        String logoUrl
) {
    public static SocialResponse fromEntity(Social social) {
        return new SocialResponse(
                social.getName(),
                social.getLogoUrl()
        );
    }
}
