package be.portal.job.entities;

import be.portal.job.enums.AdvertiserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "company_advertiser")
public class CompanyAdvertiser extends BaseEntity<Long>{

    @Column(name = "advertiser_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private AdvertiserRole advertiserRole;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


}
