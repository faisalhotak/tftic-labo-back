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
@Table(name = "job_advertiser")
public class JobAdvertiser extends User {

    @OneToMany(mappedBy = "jobAdvertiser")
    private Set<CompanyAdvertiser> companyAdvertisers;
}
