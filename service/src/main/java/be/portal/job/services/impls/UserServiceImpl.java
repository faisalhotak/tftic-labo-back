package be.portal.job.services.impls;

import be.portal.job.dtos.auth.requests.JobAdvertiserRegisterRequest;
import be.portal.job.dtos.auth.requests.JobSeekerRegisterRequest;
import be.portal.job.dtos.auth.responses.UserTokenResponse;
import be.portal.job.dtos.common.EmailRequest;
import be.portal.job.dtos.common.IdRequest;
import be.portal.job.dtos.user.requests.JobAdvertiserUpdateRequest;
import be.portal.job.dtos.user.requests.JobSeekerUpdateRequest;
import be.portal.job.dtos.user.responses.JobAdvertiserResponse;
import be.portal.job.dtos.user.responses.JobSeekerResponse;
import be.portal.job.entities.*;
import be.portal.job.exceptions.NotAllowedException;
import be.portal.job.exceptions.NotFoundException;
import be.portal.job.exceptions.auth.UserAlreadyExistsException;
import be.portal.job.exceptions.auth.UserNotFoundException;
import be.portal.job.dtos.user.responses.UserResponse;
import be.portal.job.mappers.user.UserMapper;
import be.portal.job.repositories.*;
import be.portal.job.services.IAuthService;
import be.portal.job.services.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final JobAdvertiserRepository jobAdvertiserRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final IAuthService authService;

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::fromUser)
                .toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userMapper.fromUser(
                userRepository.findById(id).orElseThrow(UserNotFoundException::new)
        );
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        return userMapper.fromUser(
                userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new)
        );
    }

    @Override
    public List<JobAdvertiserResponse> getAllAdvertisers() {
        return jobAdvertiserRepository.findAll().stream()
                .map(userMapper::fromJobAdvertiser)
                .toList();
    }

    @Override
    public List<JobSeekerResponse> getAllSeekers() {
        return jobSeekerRepository.findAll().stream()
                .map(userMapper::fromJobSeeker)
                .toList();
    }

    @Override
    @Transactional
    public JobAdvertiserResponse addAdvertiser(JobAdvertiserRegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        Role role = roleRepository.findByName(request.getRoleName())
                .orElseThrow(() -> new NotFoundException(request.getRoleName() + " role not found"));

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        JobAdvertiser jobAdvertiser = userMapper.toJobAdvertiser(request, Set.of(role));

        return userMapper.fromJobAdvertiser(jobAdvertiserRepository.save(jobAdvertiser));
    }

    @Override
    @Transactional
    public JobSeekerResponse addSeeker(JobSeekerRegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        Role role = roleRepository.findByName(request.getRoleName())
                .orElseThrow(() -> new NotFoundException(request.getRoleName() + " role not found"));

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        JobSeeker jobSeeker = userMapper.toJobSeeker(request, Set.of(role));

        return userMapper.fromJobSeeker(jobSeekerRepository.save(jobSeeker));
    }

    @Override
    @Transactional
    public JobAdvertiserResponse updateAdvertiser(Long id, JobAdvertiserUpdateRequest request) {
        JobAdvertiser jobAdvertiser = jobAdvertiserRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userMapper.updateEntityFromRequest(request, jobAdvertiser);

        return userMapper.fromJobAdvertiser(userRepository.save(jobAdvertiser));
    }

    @Override
    @Transactional
    public JobSeekerResponse updateSeeker(Long id, JobSeekerUpdateRequest request) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userMapper.updateEntityFromRequest(request, jobSeeker);

        return userMapper.fromJobSeeker(userRepository.save(jobSeeker));
    }

    @Override
    @Transactional
    public UserResponse deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userRepository.deleteById(id);

        return userMapper.fromUser(user);
    }

    @Override
    public UserResponse triggerLock(IdRequest request, boolean isLocked) {
        User user = userRepository.findById(request.id()).orElseThrow(UserNotFoundException::new);

        if (!user.isAccountNonLocked() == isLocked) {
            throw new NotAllowedException(String.format("User field 'isLocked' already defined to '%s'", isLocked));
        }

        user.setLocked(isLocked);

        return userMapper.fromUser(userRepository.save(user));
    }

    @Override
    public UserTokenResponse impersonateUserById(IdRequest request) {
        User user = userRepository.findById(request.id()).orElseThrow(UserNotFoundException::new);

        return authService.impersonateUser(user);
    }

    @Override
    public UserTokenResponse impersonateUserByEmail(EmailRequest request) {
        User user = userRepository.findByEmail(request.email()).orElseThrow(UserNotFoundException::new);

        return authService.impersonateUser(user);
    }
}
