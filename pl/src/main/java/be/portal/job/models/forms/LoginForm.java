package be.portal.job.models.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginForm(
        @NotBlank
        @Email
        String username,

        @NotBlank
        String password
) {}
