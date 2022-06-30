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
@Table(name = "technics")
public class Technics {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_technic")
        private Integer id_technic;

        @Column(name = "category",  nullable = false)
        private String category;

        @Column(name = "name_technic", nullable = false)
        private String name_technic;

        @Column(name = "count_technic")
        private Integer count_technic;

        @Column(name = "id_unit")
        private Integer id_unit;

        @ManyToMany(mappedBy = "technics")
        private List<Military_units> military_units = new ArrayList<>();

}
