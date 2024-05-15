package be.portal.job.utils.dataInitializer;

import be.portal.job.entities.Role;
import be.portal.job.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(2)
public class RoleInit implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        // Créer quelques rôles avec des valeurs arbitraires
        List<Role> roles = List.of(
                new Role("ROLE_ADMIN", "Administrateur"),
                new Role("ROLE_USER", "Utilisateur"),
                new Role("ROLE_MANAGER", "Manager"),
                new Role("ROLE_SUPERVISOR", "Superviseur")
        );

        roleRepository.saveAll(roles);
    }
}
