package be.portal.job.dtos.job_offer.requests;

import jakarta.validation.constraints.NotNull;

public record JobOfferTransferRequest(

        @NotNull(message = "Agent id is mandatory")
        Long agentId
) { }
