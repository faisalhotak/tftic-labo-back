package be.portal.job.dtos.user.requests;

import jakarta.validation.constraints.NotNull;

public record UserIdRequest(
        @NotNull(message = "User id is required")
        Long id
) { }
