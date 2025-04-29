package etf.unibl.org.ip.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "manager")
public class ManagerEntity {
    @Id
    @Column(name = "id_person", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_person", nullable = false)
    private PersonEntity person;

    @OneToMany(mappedBy = "idPerson")
    @JsonIgnore
    private Set<MarketingContentEntity> marketingContents = new LinkedHashSet<>();


}