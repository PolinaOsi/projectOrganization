package projectOrganization.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "departments")
public class Departments {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_department")
        private Integer id_department;

        @Column(name = "id_platoon")
        private Integer id_platoon;

        @ManyToOne
        private Platoons platoons;

    public Departments(Integer id_department, Integer id_platoon) {
        this.id_department = id_department;
        this.id_platoon = id_platoon;
    }

    public Departments() { }

    public Integer getId_department() { return id_department; }

    public void setId_department(Integer id_department) { this.id_department = id_department; }

    public Integer getId_platoon() { return id_platoon; }

    public void setId_platoon(Integer id_platoon) { this.id_platoon = id_platoon; }
}
