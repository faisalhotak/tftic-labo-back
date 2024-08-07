package be.portal.job.utils.datainitializer;

import be.portal.job.entities.ContractType;
import be.portal.job.repositories.ContractTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(5)
public class ContractTypeInit implements CommandLineRunner {

    private final ContractTypeRepository contractTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create different types of contracts with arbitrary values
        List<ContractType> contractTypes = List.of(
                new ContractType("CDI", "Contrat à Durée Indéterminée"),
                new ContractType("CDD", "Contrat à Durée Déterminée"),
                new ContractType("Stage", "Contrat de Stage")
        );

        contractTypeRepository.saveAll(contractTypes);
    }
}
