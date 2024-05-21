package be.portal.job.repositories;

import be.portal.job.entities.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractTypeRepository extends JpaRepository<ContractType, Long> {
}

