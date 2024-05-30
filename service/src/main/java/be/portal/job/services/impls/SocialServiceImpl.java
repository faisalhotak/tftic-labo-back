package be.portal.job.services.impls;

import be.portal.job.dtos.social.requests.SocialRequest;
import be.portal.job.dtos.social.responses.SocialResponse;
import be.portal.job.entities.Social;
import be.portal.job.exceptions.NotAllowedException;
import be.portal.job.exceptions.social.SocialNotFoundException;
import be.portal.job.mappers.social.SocialMapper;
import be.portal.job.repositories.SocialRepository;
import be.portal.job.services.ISocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialServiceImpl implements ISocialService {

    private final SocialRepository socialRepository;
    private final SocialMapper socialMapper;

    @Override
    public List<SocialResponse> getAll() {
        return socialRepository.findAll()
                .stream()
                .map(socialMapper::fromEntity)
                .toList();
    }

    @Override
    public SocialResponse getSocialById(Long id) {

        Social social = socialRepository.findById(id).orElseThrow(SocialNotFoundException::new);

        return socialMapper.fromEntity(social);
    }

    @Override
    public SocialResponse addSocial(SocialRequest socialRequest) {

        if (socialRepository.findByName(socialRequest.name()).isPresent()) {
            throw new NotAllowedException("Social already exists");
        }

        Social social = socialMapper.toEntity(socialRequest);

        return socialMapper.fromEntity(socialRepository.save(social));
    }

    @Override
    public SocialResponse updateSocial(Long id, SocialRequest social) {

        Social existingSocial = socialRepository.findById(id).orElseThrow(SocialNotFoundException::new);

        socialMapper.updateEntityFromRequest(social, existingSocial);

        socialRepository.save(existingSocial);

        return socialMapper.fromEntity(existingSocial);
    }

    @Override
    public SocialResponse deleteSocial(Long id) {

        Social existingSocial = socialRepository.findById(id).orElseThrow(SocialNotFoundException::new);

        socialRepository.deleteById(id);

        return socialMapper.fromEntity(existingSocial);
    }
}
