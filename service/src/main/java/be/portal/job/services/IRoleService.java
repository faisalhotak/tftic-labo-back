package be.portal.job.services;

import be.portal.job.dtos.role.requests.RoleRequest;
import be.portal.job.dtos.role.responses.RoleResponse;

import java.util.List;

/**
 * Service interface for role management in the system.
 * Provides basic CRUD operations for roles.
 */
public interface IRoleService {

    /**
     * Retrieves a list of all roles.
     * @return a list of role responses.
     */
    List<RoleResponse> getAll();

    /**
     * Retrieves a role by its name.
     * @param name the name of the role.
     * @return a role response.
     */
    RoleResponse getByName(String name);

    /**
     * Retrieves a role by its id.
     * @param id the id of the role.
     * @return a role response.
     */
    RoleResponse getById(Long id);

    /**
     * Adds a new role.
     * @param request the role request.
     * @return a role response.
     */
    RoleResponse add(RoleRequest request);

    /**
     * Updates a role by its id.
     * @param id the id of the role to update.
     * @param request the role request.
     * @return a role response.
     */
    RoleResponse update(Long id, RoleRequest request);

    /**
     * Deletes a role by its id.
     * @param id the id of the role to delete.
     * @return a role response.
     */
    RoleResponse delete(Long id);
}
