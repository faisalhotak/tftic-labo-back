package be.portal.job.models.dtos;

import be.portal.job.entities.Role;

public record RoleDTO(
    String name,
    String description
) {
    public static RoleDTO fromEntity(Role role) {
        return new RoleDTO(
                role.getName(),
                role.getDescription()
        );
    }
}
