package be.portal.job.repositories;

import be.portal.job.entities.SkillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillDetailRepository extends JpaRepository<SkillDetail, Long> {

    @Query("SELECT sd FROM SkillDetail sd WHERE sd.name LIKE :name")
    Optional<SkillDetail> findByName(String name);
}
