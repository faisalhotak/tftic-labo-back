package be.portal.job.services;

import be.portal.job.entities.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Interface de service pour la gestion des rôles dans le système.
 * Fournit les opérations CRUD de base pour les rôles.
 */
@Service
public interface IRoleService {

    /**
     * Récupère la liste de tous les rôles.
     *
     * @return une liste de tous les rôles.
     */
    List<Role> getRoles();
    /**
     * Récupère un rôle par son nom.
     *
     * @param name le nom du rôle à rechercher.
     * @return le rôle correspondant au nom fourni, ou null s'il n'est pas trouvé.
     */
    Optional<Role> getRoleByName(String name);
    /**
     * Récupère un rôle par son identifiant unique.
     *
     * @param id l'identifiant du rôle à rechercher.
     * @return le rôle correspondant à l'identifiant fourni, ou null s'il n'est pas trouvé.
     */
    Role getRoleById(Long id);
    /**
     * Ajoute un nouveau rôle dans le système.
     *
     * @param role le rôle à ajouter.
     * @return le rôle ajouté avec son identifiant généré.
     */
    Role addRole(Role role);
    /**
     * Met à jour un rôle existant.
     *
     * @param id l'identifiant du rôle à mettre à jour.
     * @param role les nouvelles informations du rôle.
     * @return le rôle mis à jour, ou null si le rôle n'existe pas.
     */
    Role updateRole(Long id, Role role);
    /**
     * Supprime un rôle par son identifiant.
     *
     * @param id l'identifiant du rôle à supprimer.
     */
    void deleteRole(Long id);
}
