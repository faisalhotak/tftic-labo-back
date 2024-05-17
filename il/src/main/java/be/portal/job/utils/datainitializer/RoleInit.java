package be.portal.job.utils.datainitializer;

import be.portal.job.entities.Role;
import be.portal.job.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(1)
public class RoleInit implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        // Créer quelques rôles avec des valeurs arbitraires
        List<Role> roles = List.of(
                new Role("ADMIN", "Administrateur"),
                new Role("USER", "Utilisateur"),
                new Role("MANAGER", "Manager"),
                new Role("SUPERVISOR", "Superviseur"),
                new Role("SEEKER", "Chercheur"),
                new Role("ADVERTISER", "Promoteur")
        );

        roleRepository.saveAll(roles);
    }
}
