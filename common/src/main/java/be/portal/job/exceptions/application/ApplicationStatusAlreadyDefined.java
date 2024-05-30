package be.portal.job.exceptions.application;

import be.portal.job.enums.ApplicationStatus;
import be.portal.job.exceptions.NotAllowedException;

public class ApplicationStatusAlreadyDefined extends NotAllowedException {

        public ApplicationStatusAlreadyDefined(ApplicationStatus status) {
            super(String.format("The status %s is already defined.", status));
        }
}
