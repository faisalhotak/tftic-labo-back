package be.portal.job.exceptions.auth;

public class UserNotAuthenticatedException extends RuntimeException {

        public UserNotAuthenticatedException() {
            super("You are not authenticated !");
        }
}
