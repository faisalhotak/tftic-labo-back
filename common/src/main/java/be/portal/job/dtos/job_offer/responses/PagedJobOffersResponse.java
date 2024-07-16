package be.portal.job.dtos.job_offer.responses;

import java.util.List;

public record PagedJobOffersResponse(
        List<JobOfferResponse> jobOffers,
        Integer elementsPerPage,
        Long totalElements,
        Integer totalPages
) { }
