package be.portal.job.exceptions.auth;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("Invalid password!");
    }
}
