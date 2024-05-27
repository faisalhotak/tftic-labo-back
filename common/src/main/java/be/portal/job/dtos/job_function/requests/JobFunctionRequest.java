package be.portal.job.dtos.job_function.requests;

import be.portal.job.entities.JobFunction;
import jakarta.validation.constraints.NotBlank;

public record JobFunctionRequest (
        @NotBlank(message="The job function name cannot be empty.")
        String name,
        @NotBlank(message="The job function title cannot be empty.")
        String title
) {
    public JobFunction toEntity() {
        return new JobFunctionRequest(this.name, this.title).toEntity();
    }
}
