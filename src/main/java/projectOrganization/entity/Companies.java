package projectOrganization.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "companies")
public class Companies {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_company")
        private Integer id_company;

        @Column(name = "id_unit")
        private Integer id_unit;

        @ManyToOne
        private Military_units military_units;

        @OneToMany
        private List<Platoons> platoons;

    public Companies(Integer id_company, Integer id_unit) {
        this.id_company = id_company;
        this.id_unit = id_unit;
    }

    public Companies() { }

    public Integer getId_company() { return id_company; }

    public void setId_company(Integer id_company) { this.id_company = id_company; }

    public Integer getId_unit() { return id_unit; }

    public void setId_unit(Integer id_unit) { this.id_unit = id_unit; }

    public Military_units getMilitary_units() { return military_units; }

    public void setMilitary_units(Military_units military_units) { this.military_units = military_units; }
}
