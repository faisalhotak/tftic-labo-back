package be.portal.job.specifications;

import be.portal.job.entities.ContractType;
import be.portal.job.entities.JobFunction;
import be.portal.job.entities.JobOffer;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public interface JobOfferSpecifications {

    static Specification<JobOffer> filterByParams(Map<String, String> params) {
        Specification<JobOffer> specification = Specification.where(null);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!entry.getValue().isBlank()) {
                specification = specification.and(filterBy(entry.getKey(), entry.getValue()));
            }
        }

        return specification;
    }

    static Specification<JobOffer> filterBy(String key, String value) {
        return (root, query, criteriaBuilder) ->
                switch (key) {
                    case "jobFunctionName" -> {
                        Join<JobOffer, JobFunction> jobFunctionJoin = root.join("jobFunction");
                        yield criteriaBuilder.like(jobFunctionJoin.get("name"), "%" + value + "%");
                    }

                    case "contractTypeName" -> {
                        Join<JobOffer, ContractType> contractTypeJoin = root.join("contractType");
                        yield criteriaBuilder.like(contractTypeJoin.get("name"), "%" + value + "%");
                    }

                    case "annualGrossSalaryMin" ->
                        criteriaBuilder.greaterThanOrEqualTo(root.get("annualGrossSalaryMin"), Double.parseDouble(value));

                    case "annualGrossSalaryMax" ->
                        criteriaBuilder.lessThanOrEqualTo(root.get("annualGrossSalaryMax"), Double.parseDouble(value));

                    case "activeDays" ->
                        criteriaBuilder.lessThanOrEqualTo(root.get("activeDays"), Integer.parseInt(value));

                    case "zipCity" ->
                        criteriaBuilder.like(root.get("zipCity"), "%" + value + "%");

                    default -> null;
                };
    }
}
