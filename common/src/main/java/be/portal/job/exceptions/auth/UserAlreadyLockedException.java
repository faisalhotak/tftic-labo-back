package be.portal.job.exceptions.auth;

import be.portal.job.exceptions.NotAllowedException;

public class UserAlreadyLockedException extends NotAllowedException {

        public UserAlreadyLockedException() {
            super("User already locked !");
        }
}
