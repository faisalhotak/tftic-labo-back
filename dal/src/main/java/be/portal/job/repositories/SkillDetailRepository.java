package be.portal.job.repositories;

import be.portal.job.entities.SkillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillDetailRepository extends JpaRepository<SkillDetail, Long> {

}
