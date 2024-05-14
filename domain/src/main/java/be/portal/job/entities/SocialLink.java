package be.portal.job.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "social_link")
public class SocialLink extends BaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "soc_id")
    private Long id;

    @URL(protocol = "https", message = "Le champ doit être une URL avec le protocole HTTPS.")
    @Column(name = "soc_url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "soc_link_social_id")
    private Social social;
}
