package be.portal.job.services;

import be.portal.job.entities.Role;

import java.util.List;

/**
 * Service interface for role management in the system.
 * Provides basic CRUD operations for roles.
 */
public interface IRoleService {

    /**
     * Retrieves a list of all roles.
     * @return a list of all roles.
     */
    List<Role> getRoles();

    /**
     * Retrieves a role by its name.
     * @param name the name of the role to search for.
     * @return the role corresponding to the provided name, or null if not found.
     */
    Role getRoleByName(String name);

    /**
     * Retrieves a role by its unique identifier.
     *
     * @param id the identifier of the role to search for.
     * @return the role corresponding to the provided identifier, or null if not found.
     */
    Role getRoleById(Long id);

    /**
     * Adds a new role to the system.
     * @param role the role to add.
     * @return the added role with its generated identifier.
     */
    Role addRole(Role role);

    /**
     * Updates an existing role.
     * @param id the identifier of the role to update.
     * @param role the new information for the role.
     * @return the updated role, or null if the role does not exist.
     */
    Role updateRole(Long id, Role role);

    /**
     * Deletes a role by its identifier.
     * @param id the identifier of the role to delete.
     */
    void deleteRole(Long id);
}