package be.portal.job.controllers;

import be.portal.job.dtos.social_link.requests.SocialLinkRequest;
import be.portal.job.dtos.social_link.responses.SocialLinkResponse;
import be.portal.job.services.ISocialLinkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/social-links")
@PreAuthorize("hasAnyAuthority('SEEKER', 'ADMIN')")
public class SocialLinkController {

    private final ISocialLinkService socialLinkService;

    @GetMapping
    public ResponseEntity<List<SocialLinkResponse>> getAllSocialLinks() {
        return ResponseEntity.ok(socialLinkService.getAll());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SocialLinkResponse> getSocialLinkById(@PathVariable Long id) {
        return ResponseEntity.ok(socialLinkService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<SocialLinkResponse> addSocialLink(@RequestBody @Valid SocialLinkRequest request) {
        return ResponseEntity.ok(socialLinkService.add(request));
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SocialLinkResponse> updateSocialLink(
            @PathVariable Long id,
            @RequestBody @Valid SocialLinkRequest request
    ) {
        return ResponseEntity.ok(socialLinkService.update(id, request));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SocialLinkResponse> deleteSocialLink(@PathVariable Long id) {
        return ResponseEntity.ok(socialLinkService.delete(id));
    }
}
