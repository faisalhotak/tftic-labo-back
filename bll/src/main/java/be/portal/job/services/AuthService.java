package be.portal.job.services;

import be.portal.job.entities.User;
import be.portal.job.enums.UserType;

public interface AuthService {

    /**
     * Authenticates a user with the provided email and password.
     *
     * @param email The email of the user trying to log in.
     * @param password The password of the user trying to log in.
     * @return The authenticated User object if the login is successful.
     */
    User login(String email, String password);

    /**
     * Registers a new user in the system.
     *
     * @param user The User object containing the details of the user to be registered.
     * @return The registered User object if the registration is successful.
     */
    User register(User user, UserType userType);
}
