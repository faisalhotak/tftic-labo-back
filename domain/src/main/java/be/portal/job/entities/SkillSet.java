package be.portal.job.entities;

import be.portal.job.enums.SkillLevel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "skill_set")
public class SkillSet extends BaseEntity<Long> {

    @Column(name = "skill_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private SkillLevel skillLevel;

    @Column(name = "years", nullable = false)
    private int years;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private JobSeeker jobSeeker;

    @ManyToOne
    @JoinColumn(name = "skill_detail_id", nullable = false)
    private SkillDetail skillDetail;
}
