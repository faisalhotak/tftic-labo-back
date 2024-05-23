package be.portal.job.dtos.role.responses;

import be.portal.job.entities.Role;
import jakarta.validation.constraints.NotBlank;

public record RoleResponse(

        @NotBlank(message = "Role name cannot be blank")
        String name,

        @NotBlank(message = "Role description cannot be blank")
        String description
) {
    public static RoleResponse fromEntity(Role role) {
        return new RoleResponse(
                role.getName(),
                role.getDescription()
        );
    }
}
