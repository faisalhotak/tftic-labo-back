package be.portal.job.dtos.job_offer.responses;

import java.util.List;

public record PagedJobOfferResponse(
        List<JobOfferResponse> jobOffers,
        Long totalElements,
        Integer totalPages
) { }
