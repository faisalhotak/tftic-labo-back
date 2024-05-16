package be.portal.job.models.dtos;

import be.portal.job.entities.User;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank
        String firstname,

        @NotBlank
        String lastname
) {
    public static UserDTO fromEntity(User user) {
        return new UserDTO(user.getFirstname(), user.getLastname());
    }
}
