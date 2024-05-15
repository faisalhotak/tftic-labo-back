package be.portal.job.models.forms;

import be.portal.job.entities.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RoleForm(
        @NotBlank(message = "Le nom du rôle ne peut pas être vide")
        String name,

        String description
) {
    public Role toEntity() {
        return new RoleForm(this.name, this.description).toEntity();
    }
}
