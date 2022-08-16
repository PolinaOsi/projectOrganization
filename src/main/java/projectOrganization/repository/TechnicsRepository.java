package projectOrganization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectOrganization.entity.Armaments;
import projectOrganization.entity.Technics;

import java.util.List;

@Repository
public interface TechnicsRepository extends JpaRepository<Technics, Integer> {
    List<Technics> findByCategory(String category);
}

