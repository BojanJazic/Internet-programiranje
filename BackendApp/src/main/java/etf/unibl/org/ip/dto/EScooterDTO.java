package etf.unibl.org.ip.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EScooterDTO {
    private String idVehicle;
    private Integer idManufacturer;
    private String manufacturerName;
    private String model;
    private Integer purchasePrice;
    private Byte isRented;
    private Byte isBroken;
    private String image;
    private Integer maxSpeed;

}
