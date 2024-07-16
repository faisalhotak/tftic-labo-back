package be.portal.job.repositories;

import be.portal.job.entities.Application;
import be.portal.job.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>, JpaSpecificationExecutor<Application> {

    @Query("SELECT a FROM Application a WHERE a.jobOffer.id = :jobOfferId")
    List<Application> findByJobOfferId(Long jobOfferId);

    @Query("SELECT a FROM Application a WHERE a.id = :id AND a.jobSeeker.id = :seekerId")
    Optional<Application> findByIdAndJobSeekerId(Long id, Long seekerId);

    @Query("SELECT a FROM Application a WHERE a.jobSeeker.id = :seekerId AND a.jobOffer.id = :jobOfferId")
    Optional<Application> findByJobSeekerIdAndJobOfferId(Long seekerId, Long jobOfferId);

    @Modifying
    @Query("DELETE FROM Application a WHERE a.jobOffer.id = :jobOfferId")
    void deleteAllByJobOfferId(Long jobOfferId);

    @Modifying
    @Query("UPDATE Application a SET a.applicationStatus = :status WHERE a.jobSeeker.id = :seekerId")
    void updateAllStatusByJobSeekerId(Long seekerId, ApplicationStatus status);
}
