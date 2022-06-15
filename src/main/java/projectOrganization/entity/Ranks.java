package projectOrganization.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
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

        @ManyToMany
        private List<Employees> employees;

    public Ranks(Integer id_rank, String name_rank, String category) {
        this.id_rank = id_rank;
        this.name_rank = name_rank;
        this.category = category;
    }

    public Ranks() { }

    public Integer getId_rank() { return id_rank; }

    public void setId_rank(Integer id_rank) { this.id_rank = id_rank; }

    public String getName_rank() { return name_rank; }

    public void setName_rank(String name_rank) { this.name_rank = name_rank; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }
}
