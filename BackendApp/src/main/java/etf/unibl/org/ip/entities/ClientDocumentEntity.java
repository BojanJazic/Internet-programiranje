package etf.unibl.org.ip.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "client_documents")
public class ClientDocumentEntity {
    @Id
    @Column(name = "id_person", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_person", nullable = false)
    private ClientEntity client;

    @Column(name = "id_card", nullable = false, length = 8)
    private String idCard;

    @Column(name = "passport", length = 8)
    private String passport;

    @Column(name = "driver_license_number", length = 8)
    private String driverLicenseNumber;

    @Version
    private int version;


}