package be.portal.job.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table(name = "job_advertiser")
@EqualsAndHashCode(callSuper = true)
public class JobAdvertiser extends User {

    @OneToMany(mappedBy = "jobAdvertiser")
    private Set<CompanyAdvertiser> companyAdvertisers;

    public JobAdvertiser() {
        this.companyAdvertisers = new HashSet<>();
    }
}
