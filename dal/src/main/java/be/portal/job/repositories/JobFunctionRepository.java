package be.portal.job.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import be.portal.job.entities.JobFunction;
import org.springframework.stereotype.Repository;

@Repository
public interface JobFunctionRepository extends JpaRepository<JobFunction, Long> {

    JobFunction findByName(String name);
}