package be.portal.job.exceptions.zip_city;

import be.portal.job.exceptions.NotFoundException;

public class ZipCityNotFoundException extends NotFoundException {
    public ZipCityNotFoundException() {
        super("Zip City not found");
    }
}
