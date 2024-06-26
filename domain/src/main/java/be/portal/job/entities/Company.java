package be.portal.job.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "company")
public class Company extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "website_url", nullable = false)
    private String websiteUrl;

    @Column(name = "establishment_date", nullable = false)
    private LocalDateTime establishmentDate;

    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @Column(name = "contact_phone_number", nullable = false)
    private String contactPhoneNumber;

    @Column(name = "contact_department", nullable = false)
    private String contactDepartment;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = false;

    @OneToMany(mappedBy = "company")
    private Set<CompanyAdvertiser> companyAdvertisers;

    public Company() {
        this.companyAdvertisers = new HashSet<>();
    }

    public Company(
            String name,
            String websiteUrl,
            LocalDateTime establishmentDate,
            String contactName,
            String contactPhoneNumber,
            String contactDepartment,
            boolean isActive
    ) {
        this();
        this.name = name;
        this.websiteUrl = websiteUrl;
        this.establishmentDate = establishmentDate;
        this.contactName = contactName;
        this.contactPhoneNumber = contactPhoneNumber;
        this.contactDepartment = contactDepartment;
        this.isActive = isActive;
    }
}
