package be.portal.job.dtos.role.requests;

import jakarta.validation.constraints.NotBlank;

public record RoleRequest(

        @NotBlank(message = "Role name cannot be blank")
        String name,

        @NotBlank(message = "Role description cannot be blank")
        String description
) { }
