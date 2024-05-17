package be.portal.job.services.impls;

import be.portal.job.exceptions.auth.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@Slf4j
public class CompositeUserDetailsServiceImpl implements UserDetailsService {

    private final List<UserDetailsService> services;

    public CompositeUserDetailsServiceImpl(
            JobAdvertiserDetailsServiceImpl jobAdvertiserDetailsServiceImpl,
            JobSeekerDetailsServiceImpl jobSeekerDetailsServiceImpl
    ) {
        this.services = List.of(jobAdvertiserDetailsServiceImpl, jobSeekerDetailsServiceImpl);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for (UserDetailsService service : services) {
            try {
                return service.loadUserByUsername(username);
            } catch (UsernameNotFoundException ignored) {
                log.debug("User not found in service: {}", service.getClass().getSimpleName());
            }
        }

        throw new UserNotFoundException();
    }
}
