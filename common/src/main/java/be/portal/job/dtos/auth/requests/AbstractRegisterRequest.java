package be.portal.job.dtos.auth.requests;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public abstract class AbstractRegisterRequest {

    @NotBlank(message = "There must be an email")
    @Email(message = "The email is not valid")
    private String email;

    @NotNull(message = "There must be a phone number")
    @Size(min = 8, message = "Password must be 8 characters minimum")
    private String password;

    @NotBlank(message = "There must be a firstname")
    private String firstname;

    @NotBlank(message = "There must be lastname")
    private String lastname;

    @NotBlank(message = "There must be a phone number")
    private String phoneNumber;

    @NotBlank(message = "There must be a contact email")
    @Email(message = "The contact email is not valid")
    private String contactEmail;

    @NotBlank(message = "There must be a street")
    private String street;

    @NotBlank(message = "There must be a city")
    private String city;

    @NotNull(message = "There must be a zip")
    private Integer zip;

    @NotBlank(message = "There must be a country")
    private String country;

    public abstract String getRoleName();
}
