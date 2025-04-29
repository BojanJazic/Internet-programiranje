package etf.unibl.org.ip.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseDTO {
    private String idVehicle;
    private String manufacturer;
    private String model;
    private Integer price;
}
