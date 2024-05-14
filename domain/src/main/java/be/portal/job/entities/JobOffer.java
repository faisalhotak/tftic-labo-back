package be.portal.job.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@NoArgsConstructor @AllArgsConstructor
@Getter
@Entity
@ToString
@Table(name = "job_offer")
public class JobOffer extends BaseEntity<Long> {


    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "annual_gross_salary_min", nullable = false)
    private double annualGrossSalaryMin;

    @Column(name = "annual_gross_salary_max", nullable = false)
    private double annualGrossSalaryMax;

    @Column(name = "published_date", nullable = false)
    private LocalDateTime publishedDate;

    @Column(name = "active_days", nullable = false)
    private int activeDays;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @ManyToOne
    @JoinColumn(nullable = false, name = "agent_id")
    private CompanyAdvertiser agent;

    @ManyToOne
    @JoinColumn(nullable = false, name = "contract_Type_id")
    private ContractType contractType;

    @ManyToOne
    @JoinColumn(nullable = false, name = "job_function_id")
    private JobFunction jobFunction;

}