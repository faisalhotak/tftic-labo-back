package be.portal.job.controllers.admin;

import be.portal.job.dtos.auth.requests.JobAdvertiserRegisterRequest;
import be.portal.job.dtos.auth.requests.JobSeekerRegisterRequest;
import be.portal.job.dtos.auth.responses.UserTokenResponse;
import be.portal.job.dtos.user.requests.JobAdvertiserUpdateRequest;
import be.portal.job.dtos.user.requests.JobSeekerUpdateRequest;
import be.portal.job.dtos.user.responses.JobAdvertiserResponse;
import be.portal.job.dtos.user.responses.JobSeekerResponse;
import be.portal.job.dtos.user.responses.UserResponse;
import be.portal.job.services.IProfileService;
import be.portal.job.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/v1/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminUserController {

    private final IUserService userService;
    private final IProfileService profileService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping(params = "email")
    public ResponseEntity<UserResponse> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.deleteUserAsAdmin(id));
    }

    @GetMapping("/advertisers")
    public ResponseEntity<List<JobAdvertiserResponse>> getAllAdvertisers() {
        return ResponseEntity.ok(userService.getAllAdvertisers());
    }

    @PostMapping("/advertisers")
    public ResponseEntity<JobAdvertiserResponse> addAdvertiser(@RequestBody @Valid JobAdvertiserRegisterRequest request) {
        return ResponseEntity.ok(userService.addAdvertiser(request));
    }

    @PutMapping("/advertisers/{id:^[0-9]+$}")
    public ResponseEntity<JobAdvertiserResponse> updateAdvertiser(
            @PathVariable Long id,
            @RequestBody @Valid JobAdvertiserUpdateRequest request
    ) {
        return ResponseEntity.ok(userService.updateAdvertiser(id, request));
    }

    @GetMapping("/seekers")
    public ResponseEntity<List<JobSeekerResponse>> getAllSeekers() {
        return ResponseEntity.ok(userService.getAllSeekers());
    }

    @PostMapping("/seekers")
    public ResponseEntity<JobSeekerResponse> addSeeker(@RequestBody @Valid JobSeekerRegisterRequest request) {
        return ResponseEntity.ok(userService.addSeeker(request));
    }

    @PutMapping("/seekers/{id:^[0-9]+$}")
    public ResponseEntity<JobSeekerResponse> updateSeeker(
            @PathVariable Long id,
            @RequestBody @Valid JobSeekerUpdateRequest request
    ) {
        return ResponseEntity.ok(userService.updateSeeker(id, request));
    }

    @PatchMapping("/{id:^[0-9]+$}/lock")
    public ResponseEntity<UserResponse> lockUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.triggerLock(id, true));
    }

    @PatchMapping("/{id:^[0-9]+$}/unlock")
    public ResponseEntity<UserResponse> unlockUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.triggerLock(id, false));
    }

    @PatchMapping("/{id:^[0-9]+$}/disable")
    public ResponseEntity<UserResponse> disableUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.triggerEnable(id, false));
    }

    @PatchMapping("/{id:^[0-9]+$}/enable")
    public ResponseEntity<UserResponse> enableUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.triggerEnable(id, true));
    }

    @PostMapping("/impersonate/id/{id:^[0-9]+$}")
    public ResponseEntity<UserTokenResponse> impersonateUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.impersonateUserById(id));
    }

    @PostMapping("/impersonate/email/{email}")
    public ResponseEntity<UserTokenResponse> impersonateUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.impersonateUserByEmail(email));
    }
}
