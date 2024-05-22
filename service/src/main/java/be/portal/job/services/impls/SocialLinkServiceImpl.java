package be.portal.job.services.impls;

import be.portal.job.dtos.social_link.requests.SocialLinkRequest;
import be.portal.job.dtos.social_link.responses.SocialLinkResponse;
import be.portal.job.entities.*;
import be.portal.job.exceptions.NotFoundException;
import be.portal.job.repositories.SocialLinkRepository;
import be.portal.job.repositories.SocialRepository;
import be.portal.job.services.SocialLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialLinkServiceImpl implements SocialLinkService {

    private final SocialRepository socialRepository;
    private final SocialLinkRepository socialLinkRepository;

    @Override
    public List<SocialLinkResponse> getAll() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return socialLinkRepository.findAllByUserId(user.getId()).stream()
                .map(SocialLinkResponse::fromEntity)
                .toList();
    }

    @Override
    public SocialLinkResponse getById(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        SocialLink socialLink = socialLinkRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new NotFoundException("Social link could not be found"));

        return SocialLinkResponse.fromEntity(socialLink);
    }

    @Override
    public SocialLinkResponse add(SocialLinkRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Social social = socialRepository.findById(request.socialId())
                .orElseThrow(() -> new NotFoundException("Social not found"));

        SocialLink socialLink = request.toEntity(social, user);

        return SocialLinkResponse.fromEntity(socialLinkRepository.save(socialLink));
    }

    @Override
    public SocialLinkResponse update(Long id, SocialLinkRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        SocialLink existingSocialLink = socialLinkRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new NotFoundException("Social link not found"));

        Social social = socialRepository.findById(request.socialId())
                .orElseThrow(() -> new NotFoundException("Social not found"));

        existingSocialLink.setUrl(request.url());
        existingSocialLink.setSocial(social);

        socialLinkRepository.save(existingSocialLink);

        return SocialLinkResponse.fromEntity(existingSocialLink);
    }

    @Override
    public SocialLinkResponse delete(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        SocialLink socialLink = socialLinkRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new NotFoundException("Social link not found"));

        socialLinkRepository.deleteById(id);

        return SocialLinkResponse.fromEntity(socialLink);
    }
}
