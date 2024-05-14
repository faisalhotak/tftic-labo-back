package be.portal.job.entities;

import be.portal.job.enums.DegreeType;
import be.portal.job.enums.Mention;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "education_detail")
public class EducationDetail extends BaseEntity<Long> {

    @Column(name = "institute_name", nullable = false)
    private String instituteName;

    @Column(name = "major", nullable = false)
    private String major;

    @Column(name = "degree_type", nullable = false)
    private DegreeType degreeType;

    @Column(name = "mention", nullable = false)
    private Mention mention;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "completion_date", nullable = false)
    private LocalDateTime completionDate;

    @ManyToOne()
    @JoinColumn(name = "job_seeker_id")
    private JobSeeker jobSeeker;
}
