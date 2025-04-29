package etf.unibl.org.ip.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "vehicle")
public class VehicleEntity {
    @Id
    @Column(name = "id_vehicle", nullable = false, length = 5)
    private String idVehicle;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_manufacturer", nullable = false)
    private ManufacturerEntity idManufacturer;

    @Column(name = "model", nullable = false, length = 45)
    private String model;

    @Column(name = "purchase_price", nullable = false)
    private Integer purchasePrice;

    @ColumnDefault("0")
    @Column(name = "is_rented", nullable = false)
    private Byte isRented;

    @ColumnDefault("0")
    @Column(name = "is_broken", nullable = false)
    private Byte isBroken;

    @Column(name = "image", length = 100)
    private String image;

    @Column(name = "rental_price", nullable = true)
    private Integer rentalPrice;

    @OneToMany(mappedBy = "idVehicle")
    @JsonIgnore
    private List<MalfunctionEntity> malfunctions = new ArrayList<>();

    @OneToMany(mappedBy = "idVehicle")
    @JsonIgnore
    private List<RentalEntity> rentals = new ArrayList<>();

}