package be.portal.job.repositories;

import be.portal.job.entities.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

    @Query("SELECT j FROM JobOffer j WHERE j.agent.id = :id")
    List<JobOffer> findAllByAgent(Long id);
}
