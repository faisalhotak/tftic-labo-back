package be.portal.job.exceptions.company_advertiser;

import be.portal.job.exceptions.NotAllowedException;

public class CompanyAdvertiserInsufficientRole extends NotAllowedException {
    public CompanyAdvertiserInsufficientRole() {
        super("You do not have the required role to perform this action.");
    }
}
