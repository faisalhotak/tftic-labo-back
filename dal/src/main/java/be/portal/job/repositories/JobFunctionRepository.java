package be.portal.job.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import be.portal.job.entities.JobFunction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobFunctionRepository extends JpaRepository<JobFunction, Long> {

    @Query("SELECT jf FROM JobFunction jf WHERE jf.name = :name")
    JobFunction findByName(@Param("name") String name);
}
