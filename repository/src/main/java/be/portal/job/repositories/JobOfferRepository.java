package be.portal.job.repositories;

import be.portal.job.entities.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long>, JpaSpecificationExecutor<JobOffer> {

    @Query("SELECT jo FROM JobOffer jo WHERE jo.id = :id AND jo.agent.jobAdvertiser.id= :jobAdvertiserId")
    Optional<JobOffer> findByIdAndJobAdvertiserId(Long id, Long jobAdvertiserId);

    @Query("SELECT jo FROM JobOffer jo JOIN CompanyAdvertiser ca ON jo.agent.id = ca.id WHERE ca.jobAdvertiser.id = :id")
    List<JobOffer> findAllByJobAdvertiser(Long id);

    @Query("SELECT jo.id FROM JobOffer jo WHERE jo.agent.id = :agentId")
    List<Long> findAllIdsByAgent(Long agentId);

    @Modifying
    @Query("UPDATE JobOffer jo SET jo.isActive = false WHERE jo.id IN :jobOfferIds")
    void setInactiveAllJobOffersByIds(List<Long> jobOfferIds);
    @Query("SELECT jo FROM JobOffer jo WHERE jo.agent.id = :id")
    List<JobOffer> findAllByAgentId(Long id);

    @Query("SELECT jo FROM JobOffer jo WHERE jo.agent.company.id = :id")
    List<JobOffer> findByCompanyId(Long id);

    @Modifying
    @Query("DELETE FROM JobOffer jo WHERE jo.agent.id IN :agentsIds")
    void deleteByAgentsIds(List<Long> agentsIds);

    @Query("SELECT jo FROM JobOffer jo WHERE jo.agent.company.id = :id")
    List<JobOffer> findAllByCompanyId(Long id);

    @Modifying
    @Query("UPDATE JobOffer jo SET jo.isActive = :isActive WHERE jo.agent.jobAdvertiser.id = :jobAdvertiserId")
    void updateAllActiveByJobAdvertiserId(Long jobAdvertiserId, boolean isActive);

    @Modifying
    @Query("UPDATE JobOffer jo SET jo.isActive = false WHERE jo.agent.company.id IN :companyIds")
    void setInactiveForJobOffersByCompaniesIds(List<Long> companyIds);

}
