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
@Table(name = "platoons")
public class Platoons {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_platoon")
        private Integer id_platoon;

        @ManyToOne
        @JoinColumn(name = "id_company")
        private Companies companies;

        @OneToMany(fetch = FetchType.EAGER, mappedBy = "id_department")
        private List<Departments> departments = new ArrayList<>();

}
