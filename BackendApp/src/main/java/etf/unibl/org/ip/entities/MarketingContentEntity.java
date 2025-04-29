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
@Table(name = "marketing_content")
public class MarketingContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marketing_content", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_person", nullable = false)
    private ManagerEntity idPerson;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @OneToMany(mappedBy = "idMarketingContent")
    @JsonIgnore
    private Set<PostEntity> posts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idMarketingContent")
    @JsonIgnore
    private Set<PromotionEntity> promotions = new LinkedHashSet<>();

}