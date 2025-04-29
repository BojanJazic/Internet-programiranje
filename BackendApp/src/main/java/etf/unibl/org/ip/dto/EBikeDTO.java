package etf.unibl.org.ip.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EBikeDTO {
    private String idVehicle;
    private Integer idManufacturer;
    private String manufacturerName;
    private String model;
    private Integer purchasePrice;
    private Byte isRented;
    private Byte isBroken;
    private String image;
    private Integer autonomy;
}
