package be.portal.job.repositories;

import be.portal.job.entities.Social;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocialRepository extends JpaRepository<Social, Long> {

    /**
     * Find social by name
     * @param name name of type String
     * @return optional of social
     */
    Optional<Social> findByName(String name);
}
