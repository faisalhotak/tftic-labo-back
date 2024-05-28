package be.portal.job.repositories;

import be.portal.job.entities.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractTypeRepository extends JpaRepository<ContractType, Long> {

    @Query("SELECT ct FROM ContractType ct WHERE ct.name LIKE :name")
    Optional<ContractType> findByName(String name);
}

