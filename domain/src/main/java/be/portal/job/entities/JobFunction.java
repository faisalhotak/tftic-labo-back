package be.portal.job.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@ToString
@Table(name = "job_function")
@EqualsAndHashCode(callSuper = true)
public class JobFunction extends BaseEntity<Long> {

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
