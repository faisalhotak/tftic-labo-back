package be.portal.job.dtos.job_function.requests;

import jakarta.validation.constraints.NotBlank;

public record JobFunctionRequest (
        @NotBlank(message="The job function name cannot be empty.")
        String name
) { }
