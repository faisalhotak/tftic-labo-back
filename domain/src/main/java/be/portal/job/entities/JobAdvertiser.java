package be.portal.job.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "Job_Advertiser")
@Inheritance(strategy = InheritanceType.JOINED)
public class JobAdvertiser extends BaseEntity<Long>, User {

    @OneToMany(mappedBy = "Job_Advertiser")
    private Set<CompanyAdvertiser> companyAdvertisers;

}
