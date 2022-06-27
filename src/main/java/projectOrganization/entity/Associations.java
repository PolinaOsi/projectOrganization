package projectOrganization.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "associations")
public class Associations {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_association")
        private Integer id_association;

        @Column(name = "type_association", nullable = false)
        private String type_association;

        @Column(name = "num_association")
        private Integer num_association;

        @Column(name = "name_association", nullable = false)
        private String name_association;

        @OneToMany
        private List<Military_units> military_units;

        @OneToMany
        private List<Employees> employees;

        @ManyToOne
        private Armies army;


    public Associations(Integer id_association, String type_association, Integer num_association, String name_association) {
        this.id_association = id_association;
        this.type_association = type_association;
        this.num_association = num_association;
        this.name_association = name_association;
    }

    public Associations() { }

    public Integer getId_association() { return id_association; }

    public void setId_association(Integer id_association) { this.id_association = id_association; }

    public String getType_association() { return type_association; }

    public void setType_association(String type_association) { this.type_association = type_association; }

    public Integer getNum_association() { return num_association; }

    public void setNum_association(Integer num_association) { this.num_association = num_association; }

    public String getName_association() { return name_association; }

    public void setName_association(String name_association) { this.name_association = name_association; }

    public List<projectOrganization.entity.Military_units> getMilitary_units() { return military_units; }

    public void setMilitary_units(List<projectOrganization.entity.Military_units> military_units) { military_units = military_units; }
}
