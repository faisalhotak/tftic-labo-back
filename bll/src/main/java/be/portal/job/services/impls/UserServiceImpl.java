package be.portal.job.services.impls;

import be.portal.job.entities.JobSeeker;
import be.portal.job.entities.User;
import be.portal.job.exceptions.auth.UserAlreadyExistsException;
import be.portal.job.exceptions.auth.UserNotFoundException;
import be.portal.job.models.requests.UserAddRequest;
import be.portal.job.models.requests.UserUpdateRequest;
import be.portal.job.models.responses.UserResponse;
import be.portal.job.repositories.UserRepository;
import be.portal.job.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CompositeUserDetailsServiceImpl compositeUserDetailsServiceImpl;

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(UserResponse::fromEntity)
                .toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        return UserResponse.fromEntity(
                userRepository.findById(id).orElseThrow(UserNotFoundException::new)
        );
    }

    @Override
    public UserResponse addUser(UserAddRequest request) {
        User user = request.toEntity();

        if (userRepository.existsById(user.getId())) {
            throw new UserAlreadyExistsException();
        }

        return UserResponse.fromEntity(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        user.setFirstname(request.firstname());
        user.setLastname(request.lastname());
        user.setPhoneNumber(request.phoneNumber());
        user.setContactEmail(request.contactEmail());

        if (user.getAddress() != null) {
            user.getAddress().setStreet(request.street());
            user.getAddress().setCity(request.city());
            user.getAddress().setZip(request.zip());
            user.getAddress().setCountry(request.country());
        }

        if (user instanceof JobSeeker jobSeeker) {
            jobSeeker.setGender(request.gender());
            jobSeeker.setBirthDate(request.birthDate());
        }

        return UserResponse.fromEntity(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponse deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userRepository.deleteById(id);

        return UserResponse.fromEntity(user);
    }
}
