package be.portal.job.repositories;

import be.portal.job.entities.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {

    @Query("SELECT js FROM JobSeeker js WHERE js.email = :email")
    Optional<JobSeeker> findByEmail(String email);
}
