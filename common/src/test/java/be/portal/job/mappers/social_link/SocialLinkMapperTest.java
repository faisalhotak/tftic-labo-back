package be.portal.job.mappers.social_link;

import be.portal.job.dtos.social_link.requests.SocialLinkRequest;
import be.portal.job.dtos.social_link.responses.SocialLinkResponse;
import be.portal.job.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class SocialLinkMapperTest {
    private SocialLinkMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(SocialLinkMapper.class);
    }

    @Test
    void toEntity() {
        // Arrange
        Social social = new Social("test name", "test logo url");
        SocialLinkRequest request = new SocialLinkRequest("test url", social.getId());

        JobAdvertiser jobAdvertiser = new JobAdvertiser();
        jobAdvertiser.setFirstname("test firstname");
        jobAdvertiser.setLastname("test lastname");

        // Act
        SocialLink result = mapper.toEntity(request, social, jobAdvertiser);

        // Assert
        assertNull(result.getId());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
        assertEquals(social, result.getSocial());
        assertEquals(jobAdvertiser, result.getUser());
    }

    @Test
    void fromEntity() {
        // Arrange
        Social social = new Social();

        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setFirstname("test firstname");
        jobSeeker.setLastname("test lastname");

        SocialLink entity = new SocialLink("test url", social, jobSeeker);

        // Act
        SocialLinkResponse result = mapper.fromEntity(entity);

        // Assert
        assertNotNull(result);
        assertEquals(entity.getUrl(), result.url());
        assertEquals(entity.getSocial().getName(), result.social().name());
        assertEquals(entity.getSocial().getLogoUrl(), result.social().logoUrl());
    }

    @Test
    void updateEntityFromRequest() {
        // Arrange
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setFirstname("test firstname");
        jobSeeker.setLastname("test lastname");

        Social oldSocial = new Social("test old name", "test old logo url");
        SocialLink entity = new SocialLink("test old url", oldSocial, jobSeeker);

        Social newSocial = new Social("test new name", "test new logo url");
        SocialLinkRequest request = new SocialLinkRequest("test url", newSocial.getId());

        // Act
        mapper.updateEntityFromRequest(request, newSocial, entity);

        // Assert
        assertEquals(newSocial, entity.getSocial());
        assertEquals(jobSeeker, entity.getUser());
    }
}
