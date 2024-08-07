package be.portal.job.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(name = "job_offer")
public class JobOffer extends BaseEntity<Long> {

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "annual_gross_salary_min", nullable = false)
    private double annualGrossSalaryMin;

    @Column(name = "annual_gross_salary_max", nullable = false)
    private double annualGrossSalaryMax;

    @Column(name = "active_days", nullable = false)
    private int activeDays;

    @Column(name = "expiring_date", nullable = false)
    private LocalDateTime expiringDate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(nullable = false, name = "zip_city_id")
    private ZipCity zipCity;

    @ManyToOne
    @JoinColumn(nullable = false, name = "agent_id")
    private CompanyAdvertiser agent;

    @ManyToOne
    @JoinColumn(nullable = false, name = "contract_Type_id")
    private ContractType contractType;

    @ManyToOne
    @JoinColumn(nullable = false, name = "job_function_id")
    private JobFunction jobFunction;

    public JobOffer(String description, double annualGrossSalaryMin, double annualGrossSalaryMax, int activeDays, boolean isActive, ZipCity zipCity, CompanyAdvertiser agent, ContractType contractType, JobFunction jobFunction) {
        this.description = description;
        this.annualGrossSalaryMin = annualGrossSalaryMin;
        this.annualGrossSalaryMax = annualGrossSalaryMax;
        this.activeDays = activeDays;
        this.isActive = isActive;
        this.zipCity = zipCity;
        this.agent = agent;
        this.contractType = contractType;
        this.jobFunction = jobFunction;
        this.expiringDate = LocalDateTime.now().plusDays(activeDays);
    }
}
