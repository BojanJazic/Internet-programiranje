package etf.unibl.org.ip.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "e_bike")
public class EBikeEntity {
    @Id
    @Column(name = "id_vehicle", nullable = false, length = 5)
    private String idVehicle;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_vehicle", nullable = false)
    private VehicleEntity vehicle;

    @Column(name = "autonomy", nullable = false)
    private Integer autonomy;

    @Version
    private Integer version;

}