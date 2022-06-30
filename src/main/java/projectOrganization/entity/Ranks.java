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
@Table(name = "ranks")
public class Ranks {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_rank")
        private Integer id_rank;

        @Column(name = "name_rank", nullable = false)
        private String name_rank;

        @Column(name = "category", nullable = false)
        private String category;

        @OneToMany(fetch = FetchType.EAGER, mappedBy = "id_employee")
        private List<Employees> employees = new ArrayList<>();
}
