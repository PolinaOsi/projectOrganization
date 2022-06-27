package projectOrganization.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "platoons")
public class Platoons {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_platoon")
        private Integer id_platoon;

        @Column(name = "id_company")
        private Integer id_company;

        @ManyToOne
        private Companies companies;

        @OneToMany
        private List<Departments> departments;


    public Platoons(Integer id_platoon, Integer id_company) {
        this.id_platoon = id_platoon;
        this.id_company = id_company;
    }

    public Platoons() { }

    public Integer getId_platoon() { return id_platoon; }

    public void setId_platoon(Integer id_platoon) { this.id_platoon = id_platoon; }

    public Integer getId_company() { return id_company; }

    public void setId_company(Integer id_company) { this.id_company = id_company; }
}
