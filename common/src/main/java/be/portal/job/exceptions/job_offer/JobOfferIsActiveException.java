package be.portal.job.exceptions.job_offer;

import be.portal.job.exceptions.NotAllowedException;

public class JobOfferIsActiveException extends NotAllowedException {
    public JobOfferIsActiveException() {
        super("Job offer is active.");
    }
}
