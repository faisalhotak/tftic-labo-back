package be.portal.job.services;

import be.portal.job.models.requests.UserAddRequest;
import be.portal.job.models.requests.UserUpdateRequest;
import be.portal.job.models.responses.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAll();

    UserResponse getUserById(Long id);

    UserResponse addUser(UserAddRequest request);

    UserResponse updateUser(Long id, UserUpdateRequest request);

    UserResponse deleteUser(Long id);
}
