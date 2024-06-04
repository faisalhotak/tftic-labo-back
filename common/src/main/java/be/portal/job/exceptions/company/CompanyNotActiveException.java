package be.portal.job.exceptions.company;

import be.portal.job.exceptions.NotAllowedException;

public class CompanyNotActiveException extends NotAllowedException {

    public CompanyNotActiveException() {
        super("Company is not active.");
    }
}
