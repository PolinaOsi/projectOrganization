package projectOrganization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "companies")
public class Companies {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_company")
        private Integer id_company;

        @ManyToOne
        @JoinColumn(name = "id_unit")
        private Military_units military_units;

        @OneToMany(fetch = FetchType.EAGER, mappedBy = "id_platoon")
        private List<Platoons> platoons = new ArrayList<>();

}
