package etf.unibl.org.ip.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDocumentDTO {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String avatar;
    private String idCard;
    private String passport;
    private String driverLicenseNumber;
}
