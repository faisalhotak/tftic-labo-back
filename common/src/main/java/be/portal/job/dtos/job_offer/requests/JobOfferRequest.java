package be.portal.job.dtos.job_offer.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record JobOfferRequest(

        @NotBlank(message = "Description is mandatory")
        String description,

        @NotNull(message = "Annual gross salary min is mandatory")
        Double annualGrossSalaryMin,

        @NotNull(message = "Annual gross salary max is mandatory")
        Double annualGrossSalaryMax,

        @NotNull(message = "Active days is mandatory")
        Integer activeDays,

        @NotBlank(message = "Zip city is mandatory")
        String zipCity,

        @NotNull(message = "Agent id is mandatory")
        Long agentId,

        @NotNull(message = "Company id is mandatory")
        Long contractTypeId,

        @NotNull(message = "Job function id is mandatory")
        Long jobFunctionId
) { }
