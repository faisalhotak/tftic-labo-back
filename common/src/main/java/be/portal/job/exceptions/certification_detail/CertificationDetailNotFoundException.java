package be.portal.job.exceptions.certification_detail;

import be.portal.job.exceptions.NotFoundException;

public class CertificationDetailNotFoundException extends NotFoundException {

    public CertificationDetailNotFoundException() {
        super("Certification Detail Not Found");
    }
}
