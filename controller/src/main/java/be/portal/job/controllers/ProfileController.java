package be.portal.job.controllers;

import be.portal.job.dtos.user.requests.JobAdvertiserUpdateRequest;
import be.portal.job.dtos.user.requests.JobSeekerUpdateRequest;
import be.portal.job.dtos.user.responses.*;
import be.portal.job.services.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/profile")
public class ProfileController {

    private final IProfileService profileService;

    @PreAuthorize("hasAnyAuthority('SEEKER')")
    @GetMapping("/seekers/me")
    public ResponseEntity<JobSeekerProfileResponse> getJobSeekerProfile() {
        return ResponseEntity.ok(profileService.getJobSeekerProfile());
    }

    @PreAuthorize("hasAnyAuthority('ADVERTISER')")
    @GetMapping("/advertisers/me")
    public ResponseEntity<JobAdvertiserProfileResponse> getJobAdvertiserProfile() {
        return ResponseEntity.ok(profileService.getJobAdvertiserProfile());
    }

    @PreAuthorize("hasAuthority('SEEKER')")
    @PutMapping("/update-job-seeker")
    public ResponseEntity<JobSeekerResponse> updateProfile(JobSeekerUpdateRequest jobSeekerUpdateRequest) {
        return ResponseEntity.ok(profileService.updateJobSeekerProfile(jobSeekerUpdateRequest));
    }

    @PreAuthorize("hasAuthority('ADVERTISER')")
    @PutMapping("/update-job-advertiser")
    public ResponseEntity<JobAdvertiserResponse> updateProfile(JobAdvertiserUpdateRequest jobAdvertiserUpdateRequest) {
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
