package be.portal.job.services.impls;

import be.portal.job.entities.User;
import be.portal.job.enums.UserType;
import be.portal.job.exceptions.auth.UserAlreadyExistsException;
import be.portal.job.repositories.UserRepository;
import be.portal.job.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService compositeDetailsService;

    @Override
    public User login(String email, String password) {
        User user = (User) compositeDetailsService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid password!");
        }

        return user;
    }

    @Override
    public User register(User user, UserType userType) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return user;
    }
}
