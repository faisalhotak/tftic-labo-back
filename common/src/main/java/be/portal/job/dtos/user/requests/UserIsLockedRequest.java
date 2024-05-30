package be.portal.job.dtos.user.requests;

import jakarta.validation.constraints.NotNull;

public record UserIsLockedRequest(
        @NotNull(message = "isLocked is required")
        Boolean isLocked
) { }
