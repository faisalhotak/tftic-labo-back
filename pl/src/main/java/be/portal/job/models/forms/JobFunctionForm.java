package be.portal.job.models.forms;

import be.portal.job.entities.JobFunction;
import jakarta.validation.constraints.NotBlank;

public record JobFunctionForm (
        @NotBlank(message="The role name cannot be empty.")
        String name,

        String title
) {
    public JobFunction toEntity() {
        return new JobFunctionForm(this.name, this.title).toEntity();
    }
}
