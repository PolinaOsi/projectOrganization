package projectOrganization.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private List<Military_units> military_units;

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
}
