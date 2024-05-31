package be.portal.job.dtos.common;

import jakarta.validation.constraints.NotNull;

public record IdRequest(
        @NotNull(message = "An ID must be provided.")
        Long id
) { }
