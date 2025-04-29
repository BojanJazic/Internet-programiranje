package etf.unibl.org.ip.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "location")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_location", nullable = false)
    private Integer id;

    @Column(name = "latitude", nullable = false, precision = 8, scale = 6)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false, precision = 9, scale = 6)
    private BigDecimal longitude;

    @OneToMany(mappedBy = "pickupLocation")
    @JsonIgnore
    private List<RentalEntity> pickUpLocation = new ArrayList<>();

    @OneToMany(mappedBy = "dropoffLocation")
    @JsonIgnore
    private List<RentalEntity> dropOffLocation = new ArrayList<>();

}