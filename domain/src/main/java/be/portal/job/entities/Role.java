package be.portal.job.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity<Long> implements GrantedAuthority {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Override
    public String getAuthority() {
        return name;
    }
}
