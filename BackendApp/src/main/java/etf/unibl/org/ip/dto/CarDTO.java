package etf.unibl.org.ip.dto;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarDTO {

    private String idVehicle;
    private Integer idManufacturer;
    private String manufacturerName;
    private String model;
    private Integer purchasePrice;
    private Byte isRented;
    private Byte isBroken;
    private String image;
    private LocalDate purchaseDate;
    private String description;
}
