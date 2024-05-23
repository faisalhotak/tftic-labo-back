package be.portal.job.dtos.jobOffer.requests;

import be.portal.job.entities.JobOffer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record JobOfferRequest(

        @NotBlank(message = "Description is mandatory")
        String description,

        @NotBlank(message = "Annual gross salary min is mandatory")
        Double annualGrossSalaryMin,

        @NotBlank(message = "Annual gross salary max is mandatory")
        Double annualGrossSalaryMax,

        @NotBlank(message = "Publishing date is mandatory")
        LocalDateTime publishing,

        @NotNull(message = "Active days is mandatory")
        Integer activeDays,

        @NotBlank(message = "Expiring date is mandatory")
        LocalDateTime expiringDate,

        @NotNull(message = "Active is mandatory")
        Boolean isActive,

        @NotNull(message = "Agent id is mandatory")
        Long agentId,

        @NotNull(message = "Company id is mandatory")
        Long contractTypeId,

        @NotNull(message = "Job function id is mandatory")
        Long jobFunctionId
) {
    public void updateEntity( JobOffer jobOffer) {
        jobOffer.setDescription(this.description);
        jobOffer.setAnnualGrossSalaryMin(this.annualGrossSalaryMin);
        jobOffer.setAnnualGrossSalaryMax(this.annualGrossSalaryMax);
        jobOffer.setPublishedDate(this.publishing);
        jobOffer.setActiveDays(this.activeDays);
        jobOffer.setExpiringDate(this.expiringDate);
        jobOffer.setActive(this.isActive);
    }
}
