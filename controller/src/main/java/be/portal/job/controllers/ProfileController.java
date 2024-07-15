package be.portal.job.controllers;

import be.portal.job.dtos.user.requests.JobAdvertiserUpdateRequest;
import be.portal.job.dtos.user.requests.JobSeekerUpdateRequest;
import be.portal.job.dtos.user.requests.UserUpdatePasswordRequest;
import be.portal.job.dtos.user.responses.*;
import be.portal.job.services.IProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/profile")
public class ProfileController {

    private final IProfileService profileService;

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER')")
    @PatchMapping("/change-password")
    public ResponseEntity<UserUpdatePasswordResponse> userChangePassword(
            @RequestBody @Valid UserUpdatePasswordRequest request
    ) {
        return ResponseEntity.ok(profileService.userChangePassword(request));
    }

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER')")
    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile() {
        return ResponseEntity.ok(profileService.getProfile());
    }

    @PreAuthorize("hasAuthority('SEEKER')")
    @PutMapping("/update-job-seeker")
    public ResponseEntity<JobSeekerResponse> updateProfile(
            @RequestBody @Valid JobSeekerUpdateRequest jobSeekerUpdateRequest
    ) {
        return ResponseEntity.ok(profileService.updateJobSeekerProfile(jobSeekerUpdateRequest));
    }

    @PreAuthorize("hasAuthority('ADVERTISER')")
    @PutMapping("/update-job-advertiser")
    public ResponseEntity<JobAdvertiserResponse> updateProfile(
            @RequestBody @Valid JobAdvertiserUpdateRequest jobAdvertiserUpdateRequest
    ) {
        return ResponseEntity.ok(profileService.updateJobAdvertiserProfile(jobAdvertiserUpdateRequest));
    }

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER')")
    @PatchMapping("/disable")
    public ResponseEntity<UserResponse> disableProfile() {
        return ResponseEntity.ok(profileService.disableSelf());
    }

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER')")
    @PatchMapping("/delete")
    public ResponseEntity<UserResponse> deleteProfile() {
        return ResponseEntity.ok(profileService.deleteSelf());
    }
}
