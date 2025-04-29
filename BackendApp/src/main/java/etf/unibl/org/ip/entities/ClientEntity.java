package etf.unibl.org.ip.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "client")
public class ClientEntity {
    @Id
    @Column(name = "id_person", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_person", nullable = false)
    private PersonEntity person;

//    @Column(name = "identity_card", nullable = false, length = 8)
//    private String identityCard;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "phone", nullable = false, length = 45)
    private String phone;

    @Column(name = "avatar", nullable = false, length = 45)
    private String avatar;

    @ColumnDefault("0")
    @Column(name = "is_blocked", nullable = false)
    private Byte isBlocked;

    @Version
    private int version;

//    @OneToOne
//    private ClientDocumentEntity clientDocument;

    @OneToMany(mappedBy = "idPerson")
    @JsonIgnore
    private List<RentalEntity> rentals = new ArrayList<>();



}