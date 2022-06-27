package projectOrganization.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "military_units")

    public class Military_units {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_unit")
        private Integer id_unit;

        @Column(name = "name_unit", nullable = false)
        private String name_unit;

        @Column(name = "id_association")
        private Integer id_association;

        @Column(name = "id_dislocation")
        private Integer id_dislocation;

        @OneToMany
        private List<Employees> employees;

        @ManyToMany
        @JoinTable(
                name = "military_units_armaments",
                joinColumns = @JoinColumn (name = "military_units_id_unit"),
                inverseJoinColumns = @JoinColumn (name = "armaments_id_armament")
        )
        private List<Armaments> armaments = new ArrayList<>();

        @ManyToMany(mappedBy = "military_units")
        private List<Dislocations> dislocations = new ArrayList<>();

        @ManyToOne
        private Associations associations;

        @OneToMany
        private List<Companies> companies;

        @ManyToMany
        @JoinTable(
                name = "military_units_technics",
                joinColumns = @JoinColumn (name = "military_units_id_unit"),
                inverseJoinColumns = @JoinColumn (name = "technics_id_technic")
        )
        private List<Technics> technics = new ArrayList<>();

        @OneToMany
        private List<Constructions> constructions;


    public Military_units(Integer id_unit, String name_unit, Integer id_association, Integer id_dislocation) {
        this.id_unit = id_unit;
        this.name_unit = name_unit;
        this.id_association = id_association;
        this.id_dislocation = id_dislocation;
    }
    public Military_units() { }

    public Integer getId_unit() {
        return id_unit;
    }

    public void setId_unit(Integer id_unit) {
        this.id_unit = id_unit;
    }

    public String getName_unit() {
        return name_unit;
    }

    public void setName_unit(String name_unit) {
        this.name_unit = name_unit;
    }

    public Integer getId_association() { return id_association; }

    public void setId_association(Integer id_association) { this.id_association = id_association; }

    public Integer getId_dislocation() { return id_dislocation; }

    public void setId_dislocation(Integer id_dislocation) { this.id_dislocation = id_dislocation; }

    public List<Dislocations> getDislocations() { return dislocations; }

    public void setDislocations(List<Dislocations> dislocations) {  this.dislocations = dislocations; }

    public List<Employees> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employees> employees) {
        this.employees = employees;
    }

    public void addArmament (Armaments armament) {
        armaments.add(armament);
    }

    public void addTechnic (Technics technic) { technics.add(technic); }
}
