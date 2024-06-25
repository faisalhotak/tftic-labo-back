package be.portal.job.controllers;

import be.portal.job.dtos.social.responses.SocialResponse;
import be.portal.job.services.ISocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
@RequestMapping("/v1/socials")
public class SocialController {

    private final ISocialService socialService;

    @GetMapping
    public ResponseEntity<List<SocialResponse>> getAllSocials() {
        return ResponseEntity.ok(socialService.getAll());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SocialResponse> getSocialById(@PathVariable Long id) {
        return ResponseEntity.ok(socialService.getSocialById(id));
    }
}
