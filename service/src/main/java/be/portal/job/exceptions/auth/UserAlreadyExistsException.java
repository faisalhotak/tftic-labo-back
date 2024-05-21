package be.portal.job.exceptions.auth;

import be.portal.job.exceptions.AlreadyExistsException;

public class UserAlreadyExistsException extends AlreadyExistsException {

    public UserAlreadyExistsException() {
        super("User already exists !");
    }
}
