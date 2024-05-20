package be.portal.job.models.dtos;

import be.portal.job.entities.Social;

public record SocialDTO(

        Long id,
        String name,
        String logoUrl
) {
    public static SocialDTO fromEntity(Social social) {
        return new SocialDTO(
                social.getId(),
                social.getName(),
                social.getLogoUrl()
        );
    }
}
