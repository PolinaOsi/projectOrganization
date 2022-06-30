package projectOrganization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "military_units")

public class Military_units {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unit")
    private Integer id_unit;

    @Column(name = "name_unit", nullable = false)
    private String name_unit;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id_employee")
    private List<Employees> employees = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "military_units_armaments",
            joinColumns = @JoinColumn (name = "military_units_id_unit"),
            inverseJoinColumns = @JoinColumn (name = "armaments_id_armament")
    )
    private List<Armaments> armaments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "military_units_dislocations_",
            joinColumns = @JoinColumn (name = "military_units_id_unit"),
            inverseJoinColumns = @JoinColumn (name = "dislocations_id_dislocation")
    )
    private List<Dislocations> dislocations = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_association")
    private Associations associations;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id_company")
    private List<Companies> companies = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "military_units_technics",
            joinColumns = @JoinColumn (name = "military_units_id_unit"),
            inverseJoinColumns = @JoinColumn (name = "technics_id_technic")
    )
    private List<Technics> technics = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id_construction")
    private List<Constructions> constructions = new ArrayList<>();

    public void addArmament (Armaments armament) {
        armaments.add(armament);
    }

    public void addTechnic (Technics technic) { technics.add(technic); }

    public void addDislocation (Dislocations dislocation) { dislocations.add(dislocation); }
}
