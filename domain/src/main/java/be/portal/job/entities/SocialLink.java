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
public class SocialLink extends BaseEntity<Long> {

    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "social_id", nullable = false)
    private Social social;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
