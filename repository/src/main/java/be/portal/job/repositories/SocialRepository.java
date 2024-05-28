package be.portal.job.repositories;

import be.portal.job.entities.Social;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocialRepository extends JpaRepository<Social, Long> {

    @Query("SELECT s FROM Social s WHERE s.name LIKE :name")
    Optional<Social> findByName(String name);
}
