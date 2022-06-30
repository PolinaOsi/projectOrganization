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
@Table(name = "associations")
public class Associations {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_association")
        private Integer id_association;

        @Column(name = "type_association", nullable = false)
        private String type_association;

        @Column(name = "num_association")
        private Integer num_association;

        @Column(name = "name_association", nullable = false)
        private String name_association;

        @OneToMany(fetch = FetchType.EAGER, mappedBy = "id_unit")
        private List<Military_units> military_units = new ArrayList<>();

        @OneToMany(fetch = FetchType.EAGER, mappedBy = "id_employee")
        private List<Employees> employees = new ArrayList<>();

        @ManyToOne
        @JoinColumn(name = "id_army")
        private Armies army;
}
