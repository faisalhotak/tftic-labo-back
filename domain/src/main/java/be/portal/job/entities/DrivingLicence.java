package be.portal.job.entities;

import be.portal.job.enums.DrivingLicenceCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
