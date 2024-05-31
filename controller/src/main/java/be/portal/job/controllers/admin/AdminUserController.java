package be.portal.job.controllers.admin;

import be.portal.job.dtos.auth.requests.JobAdvertiserRegisterRequest;
import be.portal.job.dtos.auth.requests.JobSeekerRegisterRequest;
import be.portal.job.dtos.auth.responses.UserTokenResponse;
import be.portal.job.dtos.common.EmailRequest;
import be.portal.job.dtos.common.IdRequest;
import be.portal.job.dtos.user.requests.JobAdvertiserUpdateRequest;
import be.portal.job.dtos.user.requests.JobSeekerUpdateRequest;
import be.portal.job.dtos.user.responses.JobAdvertiserResponse;
import be.portal.job.dtos.user.responses.JobSeekerResponse;
import be.portal.job.dtos.user.responses.UserResponse;
import be.portal.job.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/v1")
@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin("*")
public class AdminUserController {

    private final IUserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/users/{id:^[0-9]+$}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping(value= "/users", params = "email")
    public ResponseEntity<UserResponse> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @DeleteMapping("/users/{id:^[0-9]+$}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
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

    @PatchMapping("/users/lock")
    public ResponseEntity<UserResponse> lockUser(@RequestBody @Valid IdRequest request) {
        return ResponseEntity.ok(userService.triggerLock(request, true));
    }

    @PatchMapping("/users/unlock")
    public ResponseEntity<UserResponse> unlockUser(@RequestBody @Valid IdRequest request) {
        return ResponseEntity.ok(userService.triggerLock(request, false));
    }

    @PostMapping("/users/impersonate-id")
    public ResponseEntity<UserTokenResponse> impersonateUserById(@RequestBody @Valid IdRequest request) {
        return ResponseEntity.ok(userService.impersonateUserById(request));
    }

    @PostMapping("/users/impersonate-email")
    public ResponseEntity<UserTokenResponse> impersonateUserByEmail(@RequestBody @Valid EmailRequest request) {
        return ResponseEntity.ok(userService.impersonateUserByEmail(request));
    }
}
