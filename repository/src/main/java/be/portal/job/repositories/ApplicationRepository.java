package be.portal.job.repositories;

import be.portal.job.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("SELECT a FROM Application a WHERE a.jobSeeker.id = :seekerId")
    List<Application> findByJobSeekerId(Long seekerId);

    @Query("SELECT a FROM Application a WHERE a.id = :id AND a.jobSeeker.id = :seekerId")
    Optional<Application> findByIdAndJobSeekerId(Long id, Long seekerId);

    @Query("SELECT a FROM Application a WHERE a.jobSeeker.id = :seekerId AND a.jobOffer.id = :jobOfferId")
    Optional<Application> findByJobSeekerIdAndJobOfferId(Long seekerId, Long jobOfferId);
}
