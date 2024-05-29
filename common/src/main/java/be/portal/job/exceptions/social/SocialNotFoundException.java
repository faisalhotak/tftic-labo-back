package be.portal.job.exceptions.social;

import be.portal.job.exceptions.NotFoundException;

public class SocialNotFoundException extends NotFoundException {

    public SocialNotFoundException() {
        super("Social Not Found");
    }

}
