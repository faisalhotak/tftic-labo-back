package be.portal.job.controllers;

import be.portal.job.dtos.social.requests.SocialRequest;
import be.portal.job.dtos.social.responses.SocialResponse;
import be.portal.job.entities.Social;
import be.portal.job.services.SocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/socials")
public class SocialController {

    private final SocialService socialService;

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<SocialResponse>> getAllSocials() {
        List<SocialResponse> socials = socialService.getAllSocial().stream()
                .sorted(Comparator.comparing(Social::getId))
                .map(SocialResponse::fromEntity)
                .toList();

        return ResponseEntity.ok().body(socials);
    }

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SocialResponse> getSocialById(@PathVariable Long id) {
        SocialResponse socialResponse = SocialResponse.fromEntity(socialService.getSocialById(id));

        return ResponseEntity.ok(socialResponse);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public ResponseEntity<SocialResponse> addSocial(@RequestBody SocialRequest request) {
        Social social = socialService.addSocial(request.toEntity());

        return ResponseEntity.ok(SocialResponse.fromEntity(social));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SocialResponse> updateSocial(@PathVariable Long id, @RequestBody SocialRequest request) {
        Social social = socialService.updateSocial(id, request.toEntity());

        return ResponseEntity.ok(SocialResponse.fromEntity(social));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SocialResponse> deleteSocial(@PathVariable Long id) {
        socialService.deleteSocial(id);

        return ResponseEntity.ok().build();
    }
}
