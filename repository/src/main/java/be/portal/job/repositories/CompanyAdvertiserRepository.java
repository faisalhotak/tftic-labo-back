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
            "AND ca.advertiserRole = :advertiserRole")
    Optional<CompanyAdvertiser> findByCompanyAndAgentIdAndAdvertiserRole(Long companyId, Long agentId, AdvertiserRole advertiserRole);

    @Query("SELECT ca.id FROM CompanyAdvertiser ca WHERE ca.company.id = :companyId")
    List<Long> findAllAgentsIdsByCompany(Long companyId);

    @Modifying
    @Query("DELETE FROM CompanyAdvertiser ca WHERE ca.id IN :ids")
    void deleteByIds(List<Long> ids);

    @Query("SELECT ca FROM CompanyAdvertiser ca, JobAdvertiser ja WHERE ca.advertiserRole = 'OWNER' AND ja.isEnabled = true ORDER BY ca.company.id")
    List<CompanyAdvertiser> findAllOwner();
}
