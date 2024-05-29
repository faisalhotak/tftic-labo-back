package be.portal.job.exceptions.job_function;

import be.portal.job.exceptions.NotFoundException;

public class JobFunctionNotFoundException extends NotFoundException {
    public JobFunctionNotFoundException() {
        super("Job function not found");
    }
}
