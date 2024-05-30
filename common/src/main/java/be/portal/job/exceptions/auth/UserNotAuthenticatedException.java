package be.portal.job.exceptions.auth;

import be.portal.job.exceptions.NotAllowedException;

public class UserNotAuthenticatedException extends NotAllowedException {

        public UserNotAuthenticatedException() {
            super("You are not authenticated !");
        }
}
