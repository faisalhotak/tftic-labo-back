package be.portal.job.exceptions.job_offer;

import be.portal.job.exceptions.NotFoundException;

public class JobOfferIsActiveException extends NotFoundException {
    public JobOfferIsActiveException() {
        super("Job offer is active.");
    }
}
