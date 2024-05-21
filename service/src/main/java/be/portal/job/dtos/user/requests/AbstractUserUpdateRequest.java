package be.portal.job.dtos.user.requests;

import be.portal.job.entities.Address;
import be.portal.job.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractUserUpdateRequest<T> {
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

    public abstract void updateFields(T t);

    protected void updateUserFields(User user) {
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPhoneNumber(phoneNumber);
        user.setContactEmail(contactEmail);

        user.setAddress(new Address(street, city, zip, country));
    }
}
