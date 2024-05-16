package be.portal.job.controllers;

import be.portal.job.entities.Social;
import be.portal.job.models.dtos.SocialDTO;
import be.portal.job.models.forms.SocialForm;
import be.portal.job.services.SocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/social")
public class SocialController {

    private final SocialService socialService;

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<SocialDTO>> getAllSocials(Model model) {
        List<SocialDTO> socials = socialService.getAllSocial().stream()
                .sorted(Comparator.comparing(Social::getId))
                .map(SocialDTO::fromEntity)
                .toList();

        model.addAttribute("socials", socials);

        return ResponseEntity.ok().body(socials);
    }

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SocialDTO> getSocialById(@PathVariable Long id, Model model) {
        SocialDTO socialDto = SocialDTO.fromEntity(socialService.getSocialById(id));

        model.addAttribute("social", socialDto);

        return ResponseEntity.ok(socialDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<SocialDTO> addSocial(@RequestBody SocialForm form) {
        Social social = socialService.addSocial(form.toEntity());

        return ResponseEntity.ok(SocialDTO.fromEntity(social));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<SocialDTO> updateSocial(@PathVariable Long id, @RequestBody SocialForm form) {
        Social social = socialService.updateSocial(id, form.toEntity());

        return ResponseEntity.ok(SocialDTO.fromEntity(social));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<SocialDTO> deleteSocial(@PathVariable Long id) {
        socialService.deleteSocial(id);

        return ResponseEntity.ok().build();
    }
}
