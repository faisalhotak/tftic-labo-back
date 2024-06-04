package be.portal.job.entities;

import be.portal.job.enums.AdvertiserRole;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(name = "company_advertiser")
public class CompanyAdvertiser extends BaseEntity<Long> {

    @Column(name = "advertiser_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private AdvertiserRole advertiserRole;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @ManyToOne
    @JoinColumn(nullable = false, name = "job_advertiser_id")
    private JobAdvertiser jobAdvertiser;

    @ManyToOne
    @JoinColumn(nullable = false, name = "company_id")
    private Company company;

    public CompanyAdvertiser() {
        this.isActive = true;
    }

    public CompanyAdvertiser(AdvertiserRole advertiserRole, JobAdvertiser jobAdvertiser, Company company) {
        this();
        this.advertiserRole = advertiserRole;
        this.jobAdvertiser = jobAdvertiser;
        this.company = company;
    }
}
