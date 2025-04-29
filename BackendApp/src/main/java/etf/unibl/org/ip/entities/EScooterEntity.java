package etf.unibl.org.ip.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "e_scooter")
public class EScooterEntity{
    @Id
    @Column(name = "id_vehicle", nullable = false, length = 5)
    private String idVehicle;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_vehicle", nullable = false)
    private VehicleEntity vehicle;

    @Column(name = "max_speed", nullable = false)
    private Integer maxSpeed;

    @Version
    private Integer version;
}