package be.portal.job.entities;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "address")
public class Address extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addr_id")
    private Long id;

    @Column(name = "addr_name")
    private String street;

    @Column(name = "addr_city")
    private String city;

    @Column(name = "addr_zip")
    private int zip;

    @Column(name = "addr_country")
    private String country;
}
