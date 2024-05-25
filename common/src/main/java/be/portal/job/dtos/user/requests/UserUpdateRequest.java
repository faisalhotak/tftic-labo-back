package be.portal.job.dtos.user.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateRequest {
    @NotBlank(message = "There must be a firstname")
    private final String firstname;

    @NotBlank(message = "There must be lastname")
    private final String lastname;

    @NotBlank(message = "There must be a phone number")
    private final  String phoneNumber;

    @NotBlank(message = "There must be a contact email")
    @Email(message = "The contact email is not valid")
    private final String contactEmail;

    @NotBlank(message = "There must be a street")
    private final String street;

    @NotBlank(message = "There must be a city")
    private final String city;

    @NotNull(message = "There must be a zip")
    private final Integer zip;

    @NotBlank(message = "There must be a country")
    private final String country;
}
