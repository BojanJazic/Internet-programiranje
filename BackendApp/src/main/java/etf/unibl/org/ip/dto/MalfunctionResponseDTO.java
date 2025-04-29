package etf.unibl.org.ip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MalfunctionResponseDTO {
    private Integer idMalfunction; // Auto-generisani ID kvara
    private String idVehicle; // ID vozila kao string
    private String description; // Opis kvara
    private String dateTime; // Datum i vreme kao string
}