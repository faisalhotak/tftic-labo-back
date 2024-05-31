package be.portal.job.exceptions.company;

import be.portal.job.exceptions.NotAllowedException;

public class CompanyNotVerifiedOrActiveException extends NotAllowedException {

    public CompanyNotVerifiedOrActiveException() {
        super("Company is not verified or active.");
    }
}
