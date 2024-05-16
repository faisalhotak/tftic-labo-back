package be.portal.job.entities;


import be.portal.job.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "job_seeker")
public class JobSeeker extends User {

    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birthDate;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "jobSeeker")
    private Set<ExperienceDetail> experienceDetails;

    @OneToMany(mappedBy = "jobSeeker")
    private Set<CertificationDetail> certificationDetails;

    public JobSeeker() {
        this.experienceDetails = new HashSet<>();
        this.certificationDetails = new HashSet<>();
    }
}
