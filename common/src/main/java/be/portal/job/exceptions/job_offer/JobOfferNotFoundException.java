package be.portal.job.exceptions.job_offer;

import be.portal.job.exceptions.NotFoundException;

public class JobOfferNotFoundException extends NotFoundException {
    public JobOfferNotFoundException() {
        super("Job offer not found.");
    }
}
