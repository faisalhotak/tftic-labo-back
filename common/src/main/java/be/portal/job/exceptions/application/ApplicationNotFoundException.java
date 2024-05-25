package be.portal.job.exceptions.application;

import be.portal.job.exceptions.NotFoundException;

public class ApplicationNotFoundException extends NotFoundException {
    public ApplicationNotFoundException() {
        super("Application not found.");
    }
}
