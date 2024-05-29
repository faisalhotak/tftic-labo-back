package be.portal.job.exceptions.company_advertiser;

import be.portal.job.exceptions.NotFoundException;

public class CompanyAdvertiserNotFoundException extends NotFoundException {

        public CompanyAdvertiserNotFoundException() {
            super("Company advertiser not found");
        }
}
