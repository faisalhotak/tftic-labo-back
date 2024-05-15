package be.portal.job.repositories;

import be.portal.job.entities.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractTypeRepository extends JpaRepository<ContractType, Long> {
}
