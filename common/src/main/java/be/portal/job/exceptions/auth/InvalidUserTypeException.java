package be.portal.job.exceptions.auth;

import be.portal.job.exceptions.NotAllowedException;

public class InvalidUserTypeException extends NotAllowedException {
    public InvalidUserTypeException() {
        super("Invalid user type!");
    }
}
