package be.portal.job.exceptions.company;

import be.portal.job.exceptions.NotFoundException;

public class CompanyNotFoundException extends NotFoundException {

        public CompanyNotFoundException() {
            super("Company not found.");
        }
}
