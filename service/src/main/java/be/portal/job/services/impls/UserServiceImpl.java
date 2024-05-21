package be.portal.job.services.impls;

import be.portal.job.dtos.user.responses.JobAdvertiserResponse;
import be.portal.job.dtos.user.responses.JobSeekerResponse;
import be.portal.job.entities.JobAdvertiser;
import be.portal.job.entities.JobSeeker;
import be.portal.job.entities.Role;
import be.portal.job.entities.User;
import be.portal.job.exceptions.NotFoundException;
import be.portal.job.exceptions.auth.UserAlreadyExistsException;
import be.portal.job.exceptions.auth.UserNotFoundException;
import be.portal.job.dtos.auth.requests.AbstractRegisterRequest;
import be.portal.job.dtos.user.requests.AbstractUserUpdateRequest;
import be.portal.job.dtos.user.responses.AbstractUserResponse;
import be.portal.job.repositories.JobAdvertiserRepository;
import be.portal.job.repositories.JobSeekerRepository;
import be.portal.job.repositories.RoleRepository;
import be.portal.job.repositories.UserRepository;
import be.portal.job.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final JobAdvertiserRepository jobAdvertiserRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public List<AbstractUserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(AbstractUserResponse::fromEntity)
                .toList();
    }

    @Override
    public AbstractUserResponse getUserById(Long id) {
        return AbstractUserResponse.fromEntity(
                userRepository.findById(id).orElseThrow(UserNotFoundException::new)
        );
    }

    @Override
    public AbstractUserResponse getUserByEmail(String email) {
        return AbstractUserResponse.fromEntity(
                userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new)
        );
    }

    @Override
    public List<JobAdvertiserResponse> getAllAdvertisers() {
        return jobAdvertiserRepository.findAll().stream()
                .map(JobAdvertiserResponse::fromEntity)
                .toList();
    }

    @Override
    public List<JobSeekerResponse> getAllSeekers() {
        return jobSeekerRepository.findAll().stream()
                .map(JobSeekerResponse::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public JobAdvertiserResponse addAdvertiser(AbstractRegisterRequest<JobAdvertiser> request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        Role role = roleRepository.findByName(request.getRoleName())
                .orElseThrow(() -> new NotFoundException(request.getRoleName() + " role not found"));

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        JobAdvertiser jobAdvertiser = request.toEntity(role);

        return JobAdvertiserResponse.fromEntity(userRepository.save(jobAdvertiser));
    }

    @Override
    @Transactional
    public JobSeekerResponse addSeeker(AbstractRegisterRequest<JobSeeker> request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        Role role = roleRepository.findByName(request.getRoleName())
                .orElseThrow(() -> new NotFoundException(request.getRoleName() + " role not found"));

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        JobSeeker jobSeeker = request.toEntity(role);

        return JobSeekerResponse.fromEntity(userRepository.save(jobSeeker));
    }

    @Override
    @Transactional
    public AbstractUserResponse updateAdvertiser(Long id, AbstractUserUpdateRequest<JobAdvertiser> request) {
        JobAdvertiser jobAdvertiser = jobAdvertiserRepository.findById(id).orElseThrow(UserNotFoundException::new);

        request.updateFields(jobAdvertiser);

        return AbstractUserResponse.fromEntity(userRepository.save(jobAdvertiser));
    }

    @Override
    @Transactional
    public AbstractUserResponse updateSeeker(Long id, AbstractUserUpdateRequest<JobSeeker> request) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(id).orElseThrow(UserNotFoundException::new);

        request.updateFields(jobSeeker);

        return AbstractUserResponse.fromEntity(userRepository.save(jobSeeker));
    }

    @Override
    @Transactional
    public AbstractUserResponse deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userRepository.deleteById(id);

        return AbstractUserResponse.fromEntity(user);
    }
}
