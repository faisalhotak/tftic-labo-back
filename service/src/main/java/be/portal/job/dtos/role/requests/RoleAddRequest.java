package be.portal.job.dtos.role.requests;

import be.portal.job.entities.Role;
import jakarta.validation.constraints.NotBlank;

public record RoleAddRequest(

        @NotBlank(message = "Role name cannot be blank")
        String name,

        @NotBlank(message = "Role description cannot be blank")
        String description
) {
        public Role toEntity() {
                return new Role(name, description);
        }
}
