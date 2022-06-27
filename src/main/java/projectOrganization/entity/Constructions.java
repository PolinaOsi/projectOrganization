package projectOrganization.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "constructions")
public class Constructions {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_construction")
        private Integer id_construction;

        @Column(name = "name_construction", nullable = false)
        private String name_construction;

        @Column(name = "id_unit")
        private Integer id_unit;

        @ManyToOne
        private Military_units military_units;

        public Constructions() { }

        public Constructions(Integer id_construction, String name_construction, Integer id_unit) {
                this.id_construction = id_construction;
                this.name_construction = name_construction;
                this.id_unit = id_unit;
        }

        public Integer getId_construction() { return id_construction; }

        public void setId_construction(Integer id_construction) { this.id_construction = id_construction; }

        public String getName_construction() { return name_construction; }

        public void setName_construction(String name_construction) { this.name_construction = name_construction; }

        public Integer getId_unit() { return id_unit; }

        public void setId_unit(Integer id_unit) { this.id_unit = id_unit; }

        public Military_units getMilitary_units() { return military_units; }

        public void setMilitary_units(Military_units military_units) { this.military_units = military_units; }
}
