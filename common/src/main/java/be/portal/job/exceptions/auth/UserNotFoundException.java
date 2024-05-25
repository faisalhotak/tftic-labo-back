package be.portal.job.exceptions.auth;

import be.portal.job.exceptions.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
        super("User does not exist !");
    }
}
