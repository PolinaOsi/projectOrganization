package projectOrganization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "constructions")
public class Constructions {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_construction")
        private Integer id_construction;

        @Column(name = "name_construction", nullable = false)
        private String name_construction;

        @ManyToOne
        @JoinColumn(name = "id_unit")
        private Military_units military_units;
}
