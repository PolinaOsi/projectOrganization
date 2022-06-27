package projectOrganization.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "armaments")
public class Armaments {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_armament")
        private Integer id_armament;

        @Column(name = "category", nullable = false)
        private String category;

        @Column(name = "name_armament", nullable = false)
        private String name_armament;

        @Column(name = "count_armament")
        private Integer count_armament;

        @Column(name = "id_unit")
        private Integer id_unit;

        @ManyToMany(mappedBy = "armaments")
        private List<Military_units> military_units = new ArrayList<>();

    public Armaments(Integer id_armament, String name_armament, Integer count_armament, Integer id_unit) {
        this.id_armament = id_armament;
        this.name_armament = name_armament;
        this.count_armament = count_armament;
        this.id_unit = id_unit;
    }

    public Armaments() { }

    public Integer getId_armament() { return id_armament; }

    public void setId_armament(Integer id_armament) { this.id_armament = id_armament; }

    public String getName_armament() { return name_armament; }

    public void setName_armament(String name_armament) { this.name_armament = name_armament; }

    public Integer getCount_armament() { return count_armament; }

    public void setCount_armament(Integer count_armament) { this.count_armament = count_armament; }

    public Integer getId_unit() { return id_unit; }

    public void setId_unit(Integer id_unit) { this.id_unit = id_unit; }

    public List<Military_units> getMilitary_units() {
        return military_units;
    }

    public void setMilitary_units(List<Military_units> military_units) {
        this.military_units = military_units;
    }

}
