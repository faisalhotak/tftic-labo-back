package be.portal.job.dtos.auth.requests;

import be.portal.job.entities.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public abstract class AbstractRegisterRequest<T extends User> {

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

    public abstract T toEntity(Role role);

    protected void fillUserDetails(User user, Role role) {
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPhoneNumber(phoneNumber);
        user.setContactEmail(contactEmail);

        user.setAddress(new Address(street, city, zip, country));

        user.setRoles(Set.of(role));
    }
}
