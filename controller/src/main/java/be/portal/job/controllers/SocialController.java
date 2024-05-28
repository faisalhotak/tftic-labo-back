package be.portal.job.controllers;

import be.portal.job.dtos.social.requests.SocialRequest;
import be.portal.job.dtos.social.responses.SocialResponse;
import be.portal.job.services.ISocialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/socials")
public class SocialController {

    private final ISocialService socialService;

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<SocialResponse>> getAllSocials() {
        return ResponseEntity.ok(socialService.getAll());
    }

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SocialResponse> getSocialById(@PathVariable Long id) {
        return ResponseEntity.ok(socialService.getSocialById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public ResponseEntity<SocialResponse> addSocial(@RequestBody @Valid SocialRequest socialRequest) {
        return ResponseEntity.ok(socialService.addSocial(socialRequest));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SocialResponse> updateSocial(@PathVariable @Valid Long id, @RequestBody SocialRequest social) {
        return ResponseEntity.ok(socialService.updateSocial(id, social));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SocialResponse> deleteSocial(@PathVariable Long id) {
        return ResponseEntity.ok(socialService.deleteSocial(id));
    }
}
