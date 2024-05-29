package be.portal.job.repositories;

import be.portal.job.entities.SkillSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillSetRepository extends JpaRepository<SkillSet, Long> {

    @Query("SELECT ss FROM SkillSet ss WHERE ss.jobSeeker.id = :seekerId")
    List<SkillSet> findByJobSeekerId(Long seekerId);

    @Query("SELECT ss FROM SkillSet ss WHERE ss.id = :id AND ss.jobSeeker.id = :seekerId")
    Optional<SkillSet> findByIdAndJobSeekerId(Long id, Long seekerId);
}
