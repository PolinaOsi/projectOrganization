package projectOrganization.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "dislocations")

public class Dislocations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dislocation")
    private Integer id_dislocation;

    @Column(name = "city", nullable = false)
    private String city;

    @ManyToMany
    @JoinTable(
            name = "military_units_dislocations",
            joinColumns = @JoinColumn (name = "dislocations_id_dislocation"),
            inverseJoinColumns = @JoinColumn (name = "military_units_id_unit")
    )
    private List<Military_units> military_units = new ArrayList<>();


    public Dislocations(Integer id_dislocation, String city) {
        this.id_dislocation = id_dislocation;
        this.city = city;
    }

    public Dislocations() {
    }

    public Integer getId_dislocation() {
        return id_dislocation;
    }

    public void setId_dislocation(Integer id_dislocation) {
        this.id_dislocation = id_dislocation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void addMilitaryUnits (Military_units military_unit) { military_units.add(military_unit); }
}
