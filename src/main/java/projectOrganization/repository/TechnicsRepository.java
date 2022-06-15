package projectOrganization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectOrganization.entity.Technics;

@Repository
public interface TechnicsRepository extends JpaRepository<Technics, Integer> { }

