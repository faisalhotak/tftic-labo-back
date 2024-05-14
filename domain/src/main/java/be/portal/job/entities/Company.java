package be.portal.job.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "company")
public class Company extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "website_url", nullable = false)
    private String webSiteUrl;

    @Column(name = "establishment_date", nullable = false)
    private LocalDateTime establishmentDate;

    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @Column(name = "contact_phone_numb", nullable = false)
    private int contactPhoneNumber;

    @Column(name = "contact_department", nullable = false)
    private String contactDepartment;

    @Column(name = "is_verified", nullable = false)
    private boolean isVerified;

    @OneToMany(mappedBy = "company")
    @Column(name = "company_comp_adv")
    private List<CompanyAdvertiser> companyAdvertisers;
}
