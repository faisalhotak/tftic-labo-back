package be.portal.job.exceptions.auth;

public class AccountReactivatedException extends RuntimeException {

    public AccountReactivatedException(String message) {
        super(message);
    }
}
