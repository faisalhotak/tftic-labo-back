package be.portal.job.dtos.user.responses;

import be.portal.job.entities.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserResponse {
    protected final String email;
    protected final String firstname;
    protected final String lastname;
    protected final String phoneNumber;
    protected final String contactEmail;
    protected final String street;
    protected final String city;
    protected final int zip;
    protected final String country;
    protected final Set<String> roles;

    public UserResponse(
            String email,
            String firstname,
            String lastname,
            String phoneNumber,
            String contactEmail,
            Address address,
            Set<String> roles
    ) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.contactEmail = contactEmail;
        this.street = address.getStreet();
        this.city = address.getCity();
        this.zip = address.getZip();
        this.country = address.getCountry();
        this.roles = roles;
    }
}
