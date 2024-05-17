package be.portal.job.repositories;

import be.portal.job.entities.JobAdvertiser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobAdvertiserRepository extends JpaRepository<JobAdvertiser, Long> {
}
