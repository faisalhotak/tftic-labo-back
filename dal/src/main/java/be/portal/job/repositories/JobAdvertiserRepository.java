package be.portal.job.repositories;

import be.portal.job.entities.JobAdvertiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobAdvertiserRepository extends JpaRepository<JobAdvertiser, Long> {

    @Query("SELECT ja FROM JobAdvertiser ja WHERE ja.email = :email")
    Optional<JobAdvertiser> findByEmail(String email);
}
