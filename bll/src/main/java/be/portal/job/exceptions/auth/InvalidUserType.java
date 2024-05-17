package be.portal.job.exceptions.auth;

public class InvalidUserType extends RuntimeException {
    public InvalidUserType() {
        super("Invalid user type!");
    }
}
