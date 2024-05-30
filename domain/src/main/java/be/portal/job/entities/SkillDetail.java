package be.portal.job.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "skill_detail")
public class SkillDetail extends BaseEntity<Long> {

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
