package be.portal.job.repositories;

import be.portal.job.entities.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM JobOffer jo WHERE jo.agent.id = :companyId")
    void deleteByAgent(Long companyId);
}
