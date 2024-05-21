package be.portal.job.repositories;

import be.portal.job.entities.SocialLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocialLinkRepository extends JpaRepository<SocialLink, Long> {

    /**
     * Find all social links by user id
     * @param userId user id of type Long
     * @return list of social links
     */
    List<SocialLink> findAllByUserId(Long userId);

    /**
     * Find social link by id and user id
     * @param id social link id of type Long
     * @param userId user id of type Long
     * @return optional of social link
     */
    Optional<SocialLink> findByIdAndUserId(Long id, Long userId);
}
