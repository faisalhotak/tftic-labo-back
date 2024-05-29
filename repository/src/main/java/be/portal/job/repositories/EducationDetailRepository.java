package be.portal.job.repositories;

import be.portal.job.entities.Application;
import be.portal.job.entities.EducationDetail;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EducationDetailRepository extends JpaRepository<EducationDetail, Long> {

    @Query("SELECT ed FROM EducationDetail ed WHERE ed.jobSeeker.id = :seekerId")
    List<EducationDetail> findByJobSeekerId(Long seekerId);

    @Query("SELECT ed FROM EducationDetail ed WHERE ed.id = :id AND ed.jobSeeker.id = :seekerId")
    Optional<EducationDetail> findByIdAndJobSeekerId(Long id, Long seekerId);
}
