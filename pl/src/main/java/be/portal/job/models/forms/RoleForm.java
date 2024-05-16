package be.portal.job.models.forms;

import be.portal.job.entities.Role;
import jakarta.validation.constraints.NotBlank;

public record RoleForm (
        @NotBlank(message = "The role name cannot be empty.")
        String name,
        @NotBlank(message = "The role description cannot be empty.")
        String description
) {
    public Role toEntity() {
        return new RoleForm(this.name, this.description).toEntity();
    }
}
