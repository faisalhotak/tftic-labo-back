package be.portal.job.specifications;

import be.portal.job.entities.*;
import be.portal.job.enums.ApplicationStatus;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public interface ApplicationSpecifications {

    static Specification<Application> filterByParams(Map<String, String> params) {
        Specification<Application> specification = Specification.where(null);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!entry.getValue().isBlank()) {
                specification = specification.and(filterBy(entry.getKey(), entry.getValue()));
            }
        }

        return specification;
    }

    static Specification<Application> filterBy(String key, String value) {
        return (root, query, criteriaBuilder) ->
                switch (key) {
                    case "jobSeekerId" -> criteriaBuilder.equal(root.get("jobSeeker").get("id"), Long.parseLong(value));

                    case "applicationStatus" -> {
                        ApplicationStatus status = ApplicationStatus.valueOf(value);
                        yield criteriaBuilder.equal(root.get("applicationStatus"), status);
                    }

                    case "zip" -> {
                        Join<Application, JobOffer> jobOfferJoin = root.join("jobOffer");
                        Join<JobOffer, ZipCity> zipCityJoin = jobOfferJoin.join("zipCity");
                        yield criteriaBuilder.like(zipCityJoin.get("zip"), "%" + value + "%");
                    }

                    case "city" -> {
                        Join<Application, JobOffer> jobOfferJoin = root.join("jobOffer");
                        Join<JobOffer, ZipCity> zipCityJoin = jobOfferJoin.join("zipCity");
                        yield criteriaBuilder.like(zipCityJoin.get("city"), "%" + value + "%");
                    }

                    default -> null;
                };
    }
}
