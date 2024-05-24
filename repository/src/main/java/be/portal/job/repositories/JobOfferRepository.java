package be.portal.job.repositories;

import be.portal.job.entities.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

    @Query("SELECT jo FROM JobOffer jo WHERE jo.agent.id = :id")
    List<JobOffer> findAllByAgent(Long id);

    @Modifying
    @Query("DELETE FROM JobOffer jo WHERE jo.agent.id IN :agentsIds")
    void deleteByAgentsIds(List<Long> agentsIds);
}
