package be.portal.job.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "social")
public class Social extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "logo_url", nullable = false)
    private String logoUrl;

    @OneToMany(mappedBy = "social")
    private Set<SocialLink> socialLinks;

    public Social() {
        this.socialLinks = new HashSet<>();
    }
}
