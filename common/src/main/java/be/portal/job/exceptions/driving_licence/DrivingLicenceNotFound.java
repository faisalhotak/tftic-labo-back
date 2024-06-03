package be.portal.job.exceptions.driving_licence;

import be.portal.job.exceptions.NotFoundException;

public class DrivingLicenceNotFound extends NotFoundException {

    public DrivingLicenceNotFound() {
        super("Driving licence not found");
    }
}
