package be.portal.job.mappers.role;

import be.portal.job.dtos.role.requests.RoleRequest;
import be.portal.job.dtos.role.responses.RoleResponse;
import be.portal.job.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Role toEntity(RoleRequest request);

    RoleResponse fromEntity(Role entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromRequest(RoleRequest request, @MappingTarget Role role);
}
