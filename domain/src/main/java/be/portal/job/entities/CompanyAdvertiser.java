package be.portal.job.entities;

import be.portal.job.enums.AdvertiserRole;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@ToString
@Table(name = "company_advertiser")
public class CompanyAdvertiser extends BaseEntity<Long> {

    @Column(name = "advertiser_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private AdvertiserRole advertiserRole;

    @ManyToOne
    @JoinColumn(nullable = false, name = "job_advertiser_id")
    private JobAdvertiser jobAdvertiser;

    @ManyToOne
    @JoinColumn(nullable = false, name = "company_id")
    private Company company;
}
