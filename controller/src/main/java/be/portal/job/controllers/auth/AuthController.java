package be.portal.job.controllers.auth;

import be.portal.job.dtos.auth.requests.JobAdvertiserRegisterRequest;
import be.portal.job.dtos.auth.requests.JobSeekerRegisterRequest;
import be.portal.job.dtos.auth.requests.LoginRequest;
import be.portal.job.dtos.auth.responses.UserTokenResponse;
import be.portal.job.services.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {

    private final IAuthService authService;

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public ResponseEntity<UserTokenResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/register/advertisers")
    public ResponseEntity<UserTokenResponse> registerAdvertiser(@RequestBody @Valid JobAdvertiserRegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/register/seekers")
    public ResponseEntity<UserTokenResponse> registerSeeker(@RequestBody @Valid JobSeekerRegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
