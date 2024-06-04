package be.portal.job.repositories;

import be.portal.job.entities.CompanyAdvertiser;
import be.portal.job.enums.AdvertiserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyAdvertiserRepository extends JpaRepository<CompanyAdvertiser, Long> {

    @Query("SELECT ca FROM CompanyAdvertiser ca WHERE ca.id = :id AND ca.jobAdvertiser.id = :jobAdvertiserId")
    Optional<CompanyAdvertiser> findByIdAndJobAdvertiserId(Long id, Long jobAdvertiserId);

    @Query("SELECT ca FROM CompanyAdvertiser ca WHERE ca.company.id = :companyId AND ca.jobAdvertiser.id = :agentId")
    Optional<CompanyAdvertiser> findByCompanyAndAgent(Long companyId, Long agentId);

    @Query("SELECT ca FROM CompanyAdvertiser ca " +
            "WHERE ca.company.id = :companyId " +
            "AND ca.jobAdvertiser.id = :agentId " +
            "AND ca.advertiserRole = :advertiserRole " +
            "AND ca.isActive = true")
    Optional<CompanyAdvertiser> findByCompanyAndAgentIdAndAdvertiserRole(Long companyId, Long agentId, AdvertiserRole advertiserRole);

    @Modifying
    @Query("UPDATE CompanyAdvertiser ca SET ca.isActive = :isActive WHERE ca.id IN :ids")
    void updateAllActiveByIds(List<Long> ids, boolean isActive);

    @Query("SELECT ca.id FROM CompanyAdvertiser ca WHERE ca.company.id = :companyId")
    List<Long> findAllAgentsIdsByCompany(Long companyId);
}
