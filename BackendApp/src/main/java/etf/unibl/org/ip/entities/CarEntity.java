package etf.unibl.org.ip.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "car")
public class CarEntity{
    @Id
    @Column(name = "id_vehicle", nullable = false, length = 5)
    private String idVehicle;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_vehicle", nullable = false)
    private VehicleEntity vehicle;

    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Version
    private Integer version;

}