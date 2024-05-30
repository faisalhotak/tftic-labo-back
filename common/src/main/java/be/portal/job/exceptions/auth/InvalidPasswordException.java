package be.portal.job.exceptions.auth;

import be.portal.job.exceptions.NotAllowedException;

public class InvalidPasswordException extends NotAllowedException {
    public InvalidPasswordException() {
        super("Invalid password!");
    }
}
