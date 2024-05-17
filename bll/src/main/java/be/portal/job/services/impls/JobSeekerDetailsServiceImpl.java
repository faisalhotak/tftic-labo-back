package be.portal.job.services.impls;

import be.portal.job.repositories.JobSeekerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JobSeekerDetailsServiceImpl implements UserDetailsService {

    private final JobSeekerRepository jobSeekerRepository;

    public JobSeekerDetailsServiceImpl(JobSeekerRepository jobSeekerRepository) {
        this.jobSeekerRepository = jobSeekerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return jobSeekerRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
