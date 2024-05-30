package be.portal.job.exceptions.auth;

import be.portal.job.exceptions.NotAllowedException;

public class UserAlreadyUnlockedException extends NotAllowedException {

        public UserAlreadyUnlockedException() {
            super("User already unlocked !");
        }
}
