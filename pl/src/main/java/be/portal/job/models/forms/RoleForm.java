package be.portal.job.models.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RoleForm(
        @NotBlank(message = "Le nom du rôle ne peut pas être vide")
        @Size(max = 256, message = "Le nom du rôle ne peut pas dépasser {max} caractères")
        String name,

        @Size(max = 1028, message = "La description du rôle ne peut pas dépasser {max} caractères")
        String description
) {
    public RoleForm ToEntity() {
        return new RoleForm(this.name, this.description);
    }
}
