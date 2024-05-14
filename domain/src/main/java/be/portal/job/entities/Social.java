package be.portal.job.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "social")
public class Social extends BaseEntity<Long>{

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "logo_url", nullable = false)
    @URL(protocol = "https", message = "Le champ doit être une URL avec le protocole HTTPS.")
    private String logoUrl;

    @OneToMany(mappedBy = "social")
    @Column(name = "soc_social_link")
    private List<SocialLink> socialLinks;
}
