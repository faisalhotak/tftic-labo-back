package be.portal.job.entities;

import be.portal.job.enums.DrivingLicenceCategory;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "driving_licence")
public class DrivingLicence extends BaseEntity<Long> {

    @Column(name = "driving_licence_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private DrivingLicenceCategory drivingLicenceCategory;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private JobSeeker jobSeeker;
}
