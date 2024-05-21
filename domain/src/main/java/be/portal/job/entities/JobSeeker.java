package be.portal.job.entities;

import be.portal.job.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "job_seeker")
public class JobSeeker extends User {

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "jobSeeker")
    private transient Set<ExperienceDetail> experienceDetails;

    @OneToMany(mappedBy = "jobSeeker")
    private Set<CertificationDetail> certificationDetails;

    @OneToMany(mappedBy = "jobSeeker")
    private Set<EducationDetail> educationDetails;

    @OneToMany(mappedBy = "jobSeeker")
    private Set<SkillSet> skillSets;

    @OneToMany(mappedBy = "jobSeeker")
    private Set<Application> applications;

    @OneToMany(mappedBy = "jobSeeker")
    private Set<DrivingLicence> drivingLicences;

    public JobSeeker() {
        this.experienceDetails = new HashSet<>();
        this.certificationDetails = new HashSet<>();
        this.educationDetails = new HashSet<>();
        this.skillSets = new HashSet<>();
        this.applications = new HashSet<>();
    }
}
