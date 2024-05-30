package be.portal.job.controllers.admin;

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
@RequestMapping("/api/admin/v1/socials")
@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin("*")
public class AdminSocialController {

    private final ISocialService socialService;

    @GetMapping
    public ResponseEntity<List<SocialResponse>> getAllSocials() {
        return ResponseEntity.ok(socialService.getAll());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SocialResponse> getSocialById(@PathVariable Long id) {
        return ResponseEntity.ok(socialService.getSocialById(id));
    }

    @PostMapping()
    public ResponseEntity<SocialResponse> addSocial(@RequestBody @Valid SocialRequest socialRequest) {
        return ResponseEntity.ok(socialService.addSocial(socialRequest));
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SocialResponse> updateSocial(@PathVariable Long id, @RequestBody @Valid SocialRequest social) {
        return ResponseEntity.ok(socialService.updateSocial(id, social));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SocialResponse> deleteSocial(@PathVariable Long id) {
        return ResponseEntity.ok(socialService.deleteSocial(id));
    }
}
