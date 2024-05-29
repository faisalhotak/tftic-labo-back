package be.portal.job.services.impls;

import be.portal.job.dtos.social.requests.SocialRequest;
import be.portal.job.dtos.social.responses.SocialResponse;
import be.portal.job.entities.Social;
import be.portal.job.exceptions.NotAllowedException;
import be.portal.job.exceptions.social.SocialNotFoundException;
import be.portal.job.repositories.SocialRepository;
import be.portal.job.services.ISocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialServiceImpl implements ISocialService {

    private final SocialRepository socialRepository;

    @Override
    public List<SocialResponse> getAll() {
        return socialRepository.findAll()
                .stream()
                .map(SocialResponse::fromEntity)
                .toList();
    }

    @Override
    public SocialResponse getSocialById(Long id) {

        Social social = socialRepository.findById(id).orElseThrow(SocialNotFoundException::new);

        return SocialResponse.fromEntity(social);
    }

    @Override
    public SocialResponse addSocial(SocialRequest socialRequest) {

        if (socialRepository.findByName(socialRequest.name()).isPresent()) {
            throw new NotAllowedException("Social already exists");
        }

        Social social = socialRequest.toEntity();

        return SocialResponse.fromEntity(social);
    }

    @Override
    public SocialResponse updateSocial(Long id, SocialRequest social) {

        Social existingSocial = socialRepository.findById(id).orElseThrow(SocialNotFoundException::new);

        existingSocial.setName(social.name());
        existingSocial.setLogoUrl(social.logoUrl());

        socialRepository.save(existingSocial);

        return SocialResponse.fromEntity(existingSocial);
    }

    @Override
    public SocialResponse deleteSocial(Long id) {

        Social existingSocial = socialRepository.findById(id).orElseThrow(SocialNotFoundException::new);

        socialRepository.deleteById(id);

        return SocialResponse.fromEntity(existingSocial);
    }
}
