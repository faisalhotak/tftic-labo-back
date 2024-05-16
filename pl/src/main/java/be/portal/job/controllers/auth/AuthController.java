package be.portal.job.controllers.auth;

import be.portal.job.entities.User;
import be.portal.job.models.dtos.UserTokenDTO;
import be.portal.job.models.forms.LoginForm;
import be.portal.job.models.forms.RegisterForm;
import be.portal.job.services.UserService;
import be.portal.job.utils.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public ResponseEntity<UserTokenDTO> login(@RequestBody LoginForm form){
        User user = userService.login(form.username(), form.password());
        UserTokenDTO dto = UserTokenDTO.fromEntity(user);
        String token = jwtUtils.generateToken(user);

        dto.setToken(token);

        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/register")
    public ResponseEntity<UserTokenDTO> register(@RequestBody @Valid RegisterForm form){
        User user = userService.register(form.toEntity(), form.userType());
        UserTokenDTO dto = UserTokenDTO.fromEntity(user);
        String token = jwtUtils.generateToken(user);

        dto.setToken(token);

        return ResponseEntity.ok(dto);
    }
}
