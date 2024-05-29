package be.portal.job.repositories;

import be.portal.job.entities.CertificationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificationDetailRepository extends JpaRepository<CertificationDetail, Long> {

    @Query("SELECT cd FROM CertificationDetail cd WHERE cd.jobSeeker.id = :seekerId")
    List<CertificationDetail> findByJobSeekerId(Long seekerId);

    @Query("SELECT cd FROM CertificationDetail cd WHERE cd.id = :id AND cd.jobSeeker.id = :seekerId")
    Optional<CertificationDetail> findByIdAndJobSeekerId(Long id, Long seekerId);
}
