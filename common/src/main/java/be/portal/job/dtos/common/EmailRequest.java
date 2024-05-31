package be.portal.job.dtos.common;

import jakarta.validation.constraints.NotBlank;

public record EmailRequest(
        @NotBlank(message = "Email is mandatory")
        String email
) { }
