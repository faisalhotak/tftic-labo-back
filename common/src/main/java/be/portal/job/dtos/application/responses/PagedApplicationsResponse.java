package be.portal.job.dtos.application.responses;

import java.util.List;

public record PagedApplicationsResponse(
        List<ApplicationResponse> applications,
        Integer elementsPerPage,
        Long totalElements,
        Integer totalPages
) { }
