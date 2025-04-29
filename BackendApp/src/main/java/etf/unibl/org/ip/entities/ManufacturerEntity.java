package etf.unibl.org.ip.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "manufacturer")
public class ManufacturerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_manufacturer", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "state", nullable = false, length = 45)
    private String state;

    @Column(name = "address", nullable = false, length = 45)
    private String address;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "fax", length = 20)
    private String fax;

    @Column(name = "email", length = 45)
    private String email;

    @OneToMany(mappedBy = "idManufacturer")
    @JsonIgnore
    private Set<VehicleEntity> vehicles = new LinkedHashSet<>();

}