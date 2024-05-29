package be.portal.job.dtos.role.responses;

import jakarta.validation.constraints.NotBlank;

public record RoleResponse(

        @NotBlank(message = "Role name cannot be blank")
        String name,

        @NotBlank(message = "Role description cannot be blank")
        String description
) { }
