package etf.unibl.org.ip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDocumentResponseDTO {
    private int idClient;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String phone;
    private String avatar;
    private Byte isBlocked;
    private String idCard;
    private String passport;
    private String driverLicenseNumber;
}
