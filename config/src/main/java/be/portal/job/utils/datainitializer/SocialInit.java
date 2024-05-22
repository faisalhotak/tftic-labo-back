package be.portal.job.utils.datainitializer;

import be.portal.job.entities.Social;
import be.portal.job.repositories.SocialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(9)
public class SocialInit implements CommandLineRunner {

    private final SocialRepository socialRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Social> socials = new ArrayList<>();

        socials.add(new Social("Facebook", "https://www.facebook.com/logo.png"));
        socials.add(new Social("Twitter", "https://www.twitter.com/logo.png"));
        socials.add(new Social("LinkedIn", "https://www.linkedin.com/logo.png"));
        socials.add(new Social("Instagram", "https://www.instagram.com/logo.png"));
        socials.add(new Social("Pinterest", "https://www.pinterest.com/logo.png"));
        socials.add(new Social("Snapchat", "https://www.snapchat.com/logo.png"));
        socials.add(new Social("TikTok", "https://www.tiktok.com/logo.png"));
        socials.add(new Social("YouTube", "https://www.youtube.com/logo.png"));
        socials.add(new Social("Reddit", "https://www.reddit.com/logo.png"));
        socials.add(new Social("WhatsApp", "https://www.whatsapp.com/logo.png"));

        socialRepository.saveAll(socials);
    }
}
