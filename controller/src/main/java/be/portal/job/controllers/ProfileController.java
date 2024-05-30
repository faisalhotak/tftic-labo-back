package be.portal.job.controllers;

import be.portal.job.services.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
@PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER')")
public class ProfileController {

    private final IProfileService profileService;

    @GetMapping("/disable")
    public ResponseEntity<String> disableProfile() {
        return ResponseEntity.ok(profileService.disableProfile());
    }

    @GetMapping("/delete")
    public ResponseEntity<String> deleteProfile() {
        return ResponseEntity.ok(profileService.deleteProfile());
    }
}
