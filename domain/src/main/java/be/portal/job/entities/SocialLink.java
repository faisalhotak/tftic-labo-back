package be.portal.job.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "social_link")
@EqualsAndHashCode(callSuper = true)
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
