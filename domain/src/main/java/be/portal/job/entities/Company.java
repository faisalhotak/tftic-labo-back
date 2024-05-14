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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    @Column(name = "company_name")
    private String name;

    @Column(name = "company_website_url")
    private String webSiteUrl;

    @Column(name = "company_establishment_date")
    private LocalDateTime establishmentDate;

    @Column(name = "company_contact_name")
    private String contactName;

    @Column(name = "company_contact_phone_numb")
    private int contactPhoneNumber;

    @Column(name = "company_contact_department")
    private String contactDepartment;

    @Column(name = "company_is_verified")
    private boolean isVerified;

    @OneToMany(mappedBy = "company")
    @Column(name = "company_comp_adv")
    private List<CompanyAdvertiser> companyAdvertisers;
}
