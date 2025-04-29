package etf.unibl.org.ip.dto;

import etf.unibl.org.ip.entities.LocationEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO {
    private int idPerson;
    private String idVehicle;
    private LocalDateTime rentalDate;
    private int rentalDuration;
    private int pickupLocation;
    private int dropOffLocation;
    //price bi trebalo u backend-u da se obracuna


}
