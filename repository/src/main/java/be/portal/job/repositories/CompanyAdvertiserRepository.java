package be.portal.job.repositories;

import be.portal.job.entities.CompanyAdvertiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyAdvertiserRepository extends JpaRepository<CompanyAdvertiser, Long> {

    @Query("SELECT ca FROM CompanyAdvertiser ca WHERE ca.company.id = :companyId AND ca.jobAdvertiser.id = :agentId")
    CompanyAdvertiser findByCompanyAndAgent(Long companyId, Long agentId);
}
