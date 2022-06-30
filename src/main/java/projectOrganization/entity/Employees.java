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
@Table(name = "employees")
public class Employees {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_employee")
        private Integer id_employee;

        @Column(name = "name_employee", nullable = false)
        private String name_employee;

        @Column(name = "surname_employee", nullable = false)
        private String surname_employee;

        @Column(name = "patronymic_employee", nullable = false)
        private String patronymic_employee;


        @Column(name = "date_birth", nullable = false)
        private String date_birth;

        @Column(name = "characteristic", nullable = false)
        private String characteristic;

        @ManyToOne
        @JoinColumn(name = "id_rank")
        private Ranks ranks;

        @ManyToOne
        @JoinColumn(name = "id_army")
        private Armies army;

        @ManyToOne
        @JoinColumn(name = "id_department")
        private Departments department;

        @ManyToOne
        @JoinColumn(name = "id_unit")
        private Military_units military_unit;

        @ManyToOne
        @JoinColumn(name = "id_association")
        private Associations association;

        @ManyToOne
        @JoinColumn(name = "id_boss")
        private Employees employee;

        @OneToMany(fetch = FetchType.EAGER, mappedBy = "id_employee")
        private List<Employees> employees = new ArrayList<>();
}
