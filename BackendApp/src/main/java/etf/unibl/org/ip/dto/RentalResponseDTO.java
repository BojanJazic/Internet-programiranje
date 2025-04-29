package etf.unibl.org.ip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalResponseDTO {
    private int rentalId;
    private String idVehicle;
    private String manufacturer;
    private String model;
    private String name;
    private String surname;
    private String rentalDate;
    private int rentalDuration;
    private LocationDTO pickupLocation;
    private LocationDTO dropOffLocation;
    private double price;

}
