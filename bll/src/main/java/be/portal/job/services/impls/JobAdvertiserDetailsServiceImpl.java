package be.portal.job.services.impls;

import be.portal.job.repositories.JobAdvertiserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JobAdvertiserDetailsServiceImpl implements UserDetailsService {

    private final JobAdvertiserRepository jobAdvertiserRepository;

    public JobAdvertiserDetailsServiceImpl(JobAdvertiserRepository jobAdvertiserRepository) {
        this.jobAdvertiserRepository = jobAdvertiserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return jobAdvertiserRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
