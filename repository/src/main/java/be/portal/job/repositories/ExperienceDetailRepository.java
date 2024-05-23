package be.portal.job.repositories;

import be.portal.job.entities.ExperienceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceDetailRepository extends JpaRepository<ExperienceDetail, Long>{

    List<ExperienceDetail> findAllByJobSeekerId(Long id);

}
