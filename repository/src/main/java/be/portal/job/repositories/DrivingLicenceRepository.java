package be.portal.job.repositories;

import be.portal.job.entities.DrivingLicence;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrivingLicenceRepository extends JpaRepository<DrivingLicence, Long> {

    @Query("SELECT dl FROM DrivingLicence dl WHERE dl.jobSeeker.id = :seekerId")
    List<DrivingLicence> findByJobSeekerId(Long seekerId);

    @Query("SELECT dl FROM DrivingLicence  dl WHERE dl.id = :id AND dl.jobSeeker.id = :seekerId")
    Optional<DrivingLicence> findByIdAndJobSeekerId(Long id, Long seekerId);
}

