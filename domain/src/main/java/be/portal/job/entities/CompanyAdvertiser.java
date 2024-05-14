package be.portal.job.entities;

import be.portal.job.enums.AdvertiserRole;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@ToString
@Table(name = "Company_Advertiser")
public class CompanyAdvertiser extends BaseEntity<Long> {

    @Column(nullable = false, name = "advertiser_role")
    @Enumerated(EnumType.STRING)
    private AdvertiserRole advertiserRole;

    @ManyToOne
    @JoinColumn(nullable = false, name = "job_advertiser_id")
    private JobAdvertiser jobAdvertiser;

    @ManyToOne
    @JoinColumn(nullable = false, name = "company_id")
    private Company company;
}
