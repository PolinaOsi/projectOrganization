package projectOrganization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

}
