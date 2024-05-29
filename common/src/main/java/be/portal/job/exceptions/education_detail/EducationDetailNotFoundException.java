package be.portal.job.exceptions.education_detail;

import be.portal.job.exceptions.NotFoundException;

public class EducationDetailNotFoundException extends NotFoundException {

    public EducationDetailNotFoundException() {
        super("Education detail not found");
    }
}
