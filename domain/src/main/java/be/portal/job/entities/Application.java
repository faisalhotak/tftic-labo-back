package be.portal.job.entities;

import be.portal.job.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "application")
public class Application extends BaseEntity<Long> {

    @Column(name = "apply_date", nullable = false)
    private LocalDateTime applyDate;

    @Column(name = "application_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private JobSeeker jobSeeker;

    @ManyToOne
    @JoinColumn(name = "job_offer_id", nullable = false)
    private JobOffer jobOffer;
}
