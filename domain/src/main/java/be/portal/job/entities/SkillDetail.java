package be.portal.job.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "skill_detail")
public class SkillDetail extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "skillDetail")
    private Set<SkillSet> skillSets;

    public SkillDetail() {
        this.skillSets = new HashSet<>();
    }
}
