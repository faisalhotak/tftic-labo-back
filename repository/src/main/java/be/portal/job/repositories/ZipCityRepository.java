package be.portal.job.repositories;

import be.portal.job.entities.ZipCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipCityRepository extends JpaRepository<ZipCity, Long> {
}
