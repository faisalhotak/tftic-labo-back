package be.portal.job.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "social")
@EqualsAndHashCode(callSuper = true)
public class Social extends BaseEntity<Long> {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "logo_url", nullable = false)
    private String logoUrl;
}
