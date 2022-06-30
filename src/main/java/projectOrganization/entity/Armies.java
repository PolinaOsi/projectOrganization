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
@Table(name = "armies")
public class Armies {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_army")
        private Integer id_army;

        @Column(name = "name_army", nullable = false)
        private String name_army;

        @OneToMany(fetch = FetchType.EAGER, mappedBy = "id_employee")
        private List<Employees> employees = new ArrayList<>();

        @OneToMany(fetch = FetchType.EAGER, mappedBy = "id_association")
        private List<Associations> associations = new ArrayList<>();

}
