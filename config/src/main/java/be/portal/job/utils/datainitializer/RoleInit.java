package be.portal.job.utils.datainitializer;

import be.portal.job.entities.Role;
import be.portal.job.repositories.RoleRepository;
import be.portal.job.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(0)
public class RoleInit implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Role> roles = List.of(
                new Role(Constants.ADMIN_ROLE, "Administrateur"),
                new Role(Constants.MANAGER_ROLE, "Manager"),
                new Role(Constants.SUPERVISOR_ROLE, "Superviseur"),
                new Role(Constants.ADVERTISER_ROLE, "Recruteur"),
                new Role(Constants.SEEKER_ROLE, "Chercheur d'emploi")
        );

        roleRepository.saveAll(roles);
    }
}
