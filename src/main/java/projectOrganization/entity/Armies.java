package projectOrganization.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "armies")
public class Armies {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_army")
        private Integer id_army;

        @Column(name = "name_army", nullable = false)
        private String name_army;

        @OneToMany
        private List<Employees> employees;

        @OneToMany
        private List<Associations> associations;

    public Armies(Integer id_army, String name_army) {
        this.id_army = id_army;
        this.name_army = name_army;
    }

    public Armies() { }

    public Integer getId_army() {
        return id_army;
    }

    public void setId_army(Integer id_army) {
        this.id_army = id_army;
    }

    public String getName_army() {
        return name_army;
    }

    public void setName_army(String name_army) {
        this.name_army = name_army;
    }
}
