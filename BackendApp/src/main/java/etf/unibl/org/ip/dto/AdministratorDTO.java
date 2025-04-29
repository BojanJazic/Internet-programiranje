package etf.unibl.org.ip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdministratorDTO {
    private int id;
    private String name;
    private String surname;
    private String username;
    private String role;

}
