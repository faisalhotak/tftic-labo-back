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
@Table(name = "skill_detail")
public class SkillDetail extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "skill_detail")
    private Set<SkillSet> skillSets;

    public SkillDetail() {
        this.skillSets = new HashSet<>();
    }
}
