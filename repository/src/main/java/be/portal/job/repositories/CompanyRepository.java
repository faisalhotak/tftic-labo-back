package be.portal.job.repositories;

import be.portal.job.entities.Company;
import be.portal.job.enums.AdvertiserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    /**
     * Find companies where a advertiser is the only owner.
     *
     * @param jobAdvertiserId the job advertiser id
     * @param advertiserRole  the advertiser role, normally OWNER
     * @return the list of company ids where the advertiser is the only owner
     */

    @Query(
            "SELECT c.id " +
                    "FROM Company c " +
                    "JOIN c.companyAdvertisers ca " +
                    "WHERE ca.jobAdvertiser.id = :jobAdvertiserId " +
                    "AND ca.jobAdvertiser.isEnabled = true " +
                    "AND ca.advertiserRole = :advertiserRole " +
                    "AND ( " +
                    "   SELECT COUNT(ca2) " +
                    "   FROM Company co " +
                    "   JOIN co.companyAdvertisers ca2 " +
                    "   WHERE co.id = c.id " +
                    "   AND ca2.jobAdvertiser.isEnabled = true " +
                    "   AND ca2.advertiserRole = :advertiserRole " +
                    ") = 1")
    List<Long> findCompaniesWithSingleOwnerByJobAdvertiserId(Long jobAdvertiserId, AdvertiserRole advertiserRole);

    @Modifying
    @Query("UPDATE Company c SET c.isActive = false WHERE c.id IN :companyIds")
    List<Company> setInactiveForCompanies(List<Long> companyIds);
}
