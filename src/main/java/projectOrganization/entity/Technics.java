package projectOrganization.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "technics")
public class Technics {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_technic")
        private Integer id_technic;

        @Column(name = "name_technic", nullable = false)
        private String name_technic;


        @Column(name = "count_technic")
        private Integer count_technic;


        @Column(name = "id_unit")
        private Integer id_unit;

        @ManyToMany
        private List<Military_units> military_units;

    public Technics(Integer id_technic, String name_technic, Integer count_technic, Integer id_unit) {
        this.id_technic = id_technic;
        this.name_technic = name_technic;
        this.count_technic = count_technic;
        this.id_unit = id_unit;
    }

    public Technics() { }

    public Integer getCount_technic() { return count_technic; }

    public void setCount_technic(Integer count_technic) { this.count_technic = count_technic; }

    public Integer getId_unit() { return id_unit; }

    public void setId_unit(Integer id_unit) { this.id_unit = id_unit; }
}
