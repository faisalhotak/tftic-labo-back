package be.portal.job.exceptions.job_function;

import be.portal.job.exceptions.AlreadyExistsException;

public class JobFunctionAlreadyExistsException extends AlreadyExistsException {

        public JobFunctionAlreadyExistsException() {
            super("Job function with this name already exists");
        }
}
