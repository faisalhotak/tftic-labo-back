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

    @Query("SELECT jo FROM JobOffer jo WHERE jo.agent.id = :id")
    List<JobOffer> findAllByAgentId(Long id);

    @Query("SELECT jo FROM JobOffer jo WHERE jo.agent.company.id = :id")
    List<JobOffer> findByCompanyId(Long id);

    @Query("SELECT DISTINCT jo.zipCity FROM JobOffer jo")
    List<String> findAllLocation();

    @Modifying
    @Query("UPDATE JobOffer jo SET jo.isActive = :isActive WHERE jo.agent.jobAdvertiser.id = :jobAdvertiserId")
    void updateAllActiveByJobAdvertiserId(Long jobAdvertiserId, boolean isActive);

    @Modifying
    @Query("UPDATE JobOffer jo SET jo.isActive = :isActive WHERE jo.agent.id IN :agentIds")
    void updateAllActiveByAgentIds(List<Long> agentIds, boolean isActive);

    @Modifying
    @Query("UPDATE JobOffer jo SET jo.isActive = false WHERE jo.agent.company.id IN :companyIds")
    void setInactiveForJobOffersByCompaniesIds(List<Long> companyIds);
}
