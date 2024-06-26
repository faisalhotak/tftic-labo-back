package be.portal.job.services.impls;

import be.portal.job.entities.JobAdvertiser;
import be.portal.job.entities.JobSeeker;
import be.portal.job.entities.Role;
import be.portal.job.entities.User;
import be.portal.job.exceptions.NotAllowedException;
import be.portal.job.exceptions.NotFoundException;
import be.portal.job.exceptions.auth.*;
import be.portal.job.dtos.auth.requests.LoginRequest;
import be.portal.job.dtos.auth.requests.AbstractRegisterRequest;
import be.portal.job.dtos.auth.responses.UserTokenResponse;
import be.portal.job.exceptions.auth.UserNotAuthenticatedException;
import be.portal.job.exceptions.auth.UserNotFoundException;
import be.portal.job.mappers.user.UserMapper;
import be.portal.job.repositories.RoleRepository;
import be.portal.job.repositories.UserRepository;
import be.portal.job.services.IAuthService;
import be.portal.job.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

import static be.portal.job.utils.Constants.ADMIN_ROLE;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements UserDetailsService, IAuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final UserDetailsChecker userDetailsChecker;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public UserTokenResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        if (!user.isEnabled() && user.isAccountNonLocked()) {
            user.setEnabled(true);
            userRepository.save(user);

            throw new AccountReactivatedException();
        }

        userDetailsChecker.check(user);

        String token = jwtUtils.generateToken(user);

        return UserTokenResponse.fromEntityWithToken(user, token);
    }

    @Override
    public UserTokenResponse impersonateUser(User user) {
        if (!user.isEnabled() || !user.isAccountNonLocked() || user.isExpired()) {
            throw new NotAllowedException("User cannot be impersonated due to account restrictions");
        }

        String token = jwtUtils.generateToken(user);

        return UserTokenResponse.fromEntityWithToken(user, token);
    }

    @Override
    public UserTokenResponse register(AbstractRegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        Role role = roleRepository.findByName(request.getRoleName())
                .orElseThrow(() -> new NotFoundException(request.getRoleName() + " role not found"));

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        User user = userMapper.toEntity(request, Set.of(role));

        userRepository.save(user);

        String token = jwtUtils.generateToken(user);

        return UserTokenResponse.fromEntityWithToken(user, token);
    }

    @Override
    public JobAdvertiser getAuthenticatedAdvertiser() throws UserNotAuthenticatedException {
        User authenticatedUser = getAuthenticatedUser();

        if (authenticatedUser instanceof JobAdvertiser jobAdvertiser) {
            return jobAdvertiser;
        }

        throw new UserNotAuthenticatedException();
    }

    @Override
    public JobSeeker getAuthenticatedSeeker() throws UserNotAuthenticatedException {
        User authenticatedUser = getAuthenticatedUser();

        if (authenticatedUser instanceof JobSeeker jobSeeker) {
            return jobSeeker;
        }

        throw new UserNotAuthenticatedException();
    }

    @Override
    public User getAuthenticatedUser() throws UserNotAuthenticatedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotAuthenticatedException();
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof User authenticatedUser) {
            return authenticatedUser;
        }

        throw new UserNotAuthenticatedException();
    }

    @Override
    public boolean isAdmin(User user) {
        return user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(ADMIN_ROLE));
    }
}
