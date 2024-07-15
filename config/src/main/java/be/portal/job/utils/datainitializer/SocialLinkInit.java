package be.portal.job.utils.datainitializer;

import be.portal.job.entities.Social;
import be.portal.job.entities.SocialLink;
import be.portal.job.entities.User;
import be.portal.job.repositories.SocialLinkRepository;
import be.portal.job.repositories.SocialRepository;
import be.portal.job.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(9)
public class SocialLinkInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final SocialRepository socialRepository;
    private final SocialLinkRepository socialLinkRepository;

    @Override
    public void run(String... args) throws Exception {
        List<SocialLink> socialLinks = new ArrayList<>();

        User advertiser = userRepository.findByEmail("advertiser1@example.com")
                .orElseThrow(() -> new RuntimeException("Advertiser not found"));

        User seeker = userRepository.findByEmail("seeker1@example.com")
                .orElseThrow(() -> new RuntimeException("Seeker not found"));

        Social facebook = socialRepository.findByName("Facebook")
                .orElseThrow(() -> new RuntimeException("Facebook social not found"));

        Social twitter = socialRepository.findByName("Twitter")
                .orElseThrow(() -> new RuntimeException("Twitter social not found"));

        socialLinks.add(new SocialLink("https://www.facebook.com/username", facebook, advertiser));
        socialLinks.add(new SocialLink("https://www.twitter.com/username", twitter, seeker));

        socialLinkRepository.saveAll(socialLinks);
    }
}
