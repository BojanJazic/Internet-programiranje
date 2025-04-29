package etf.unibl.org.ip.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "rental")
public class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rental", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_person", nullable = false)
    private ClientEntity idPerson;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "renting_duration", nullable = false)
    private Integer rentingDuration;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pickup_location", nullable = false)
    private LocationEntity pickupLocation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dropoff_location", nullable = false)
    private LocationEntity dropoffLocation;

    @Column(name = "rental_price", nullable = false)
    private Integer rentalPrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_vehicle", nullable = false)
    private VehicleEntity idVehicle;

    @OneToMany(mappedBy = "IdRental")
    @JsonIgnore
    private List<InvoiceEntity> invoices = new ArrayList<>();

}