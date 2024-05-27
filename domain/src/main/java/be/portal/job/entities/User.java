package be.portal.job.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public abstract class User extends BaseEntity<Long> implements UserDetails {

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "contact_email", nullable = false)
    private String contactEmail;

    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled;

    @Column(name = "is_expired", nullable = false)
    private boolean isExpired;

    @Column(name = "is_locked", nullable = false)
    private boolean isLocked;

    @Column(name = "is_credentials_expired", nullable = false)
    private boolean isCredentialsExpired;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "user")
    private Set<SocialLink> socialLinks;

    protected User() {
        this.address = new Address();
        this.isExpired = false;
        this.isLocked = false;
        this.isCredentialsExpired = false;
        this.isEnabled = true;
        this.roles = new HashSet<>();
        this.socialLinks = new HashSet<>();
    }

    protected User(String email, String password, Role role) {
        this();
        this.email = email;
        this.password = password;
        this.roles.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isCredentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
