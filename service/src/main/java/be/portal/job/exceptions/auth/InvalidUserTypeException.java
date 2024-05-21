package be.portal.job.exceptions.auth;

public class InvalidUserTypeException extends RuntimeException {
    public InvalidUserTypeException() {
        super("Invalid user type!");
    }
}
