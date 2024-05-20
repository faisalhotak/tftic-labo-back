package be.portal.job.services.impls;

import be.portal.job.entities.Social;
import be.portal.job.repositories.SocialRepository;
import be.portal.job.services.SocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialServiceImpl implements SocialService {

    private final SocialRepository socialRepository;

    @Override
    public List<Social> getAllSocial() {
        return socialRepository.findAll();
    }

    @Override
    public Social getSocialById(Long id) {
        return socialRepository.findById(id).orElseThrow();
    }

    @Override
    public Social addSocial(Social social) {
        return socialRepository.save(social);
    }

    @Override
    public Social updateSocial(Long id, Social social) {

        Social existingSocial = socialRepository.findById(id).orElseThrow();
        existingSocial.setName(social.getName());
        existingSocial.setLogoUrl(social.getLogoUrl());

        socialRepository.save(existingSocial);

        return existingSocial;
    }

    @Override
    public void deleteSocial(Long id) {
        socialRepository.deleteById(id);
    }
}
