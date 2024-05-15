package be.portal.job.repositories;

import be.portal.job.entities.JobFunction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobFunctionRepository extends JpaRepository<JobFunction, Long> {
}
