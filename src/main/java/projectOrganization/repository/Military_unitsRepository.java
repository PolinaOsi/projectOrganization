package projectOrganization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectOrganization.entity.Military_units;

@Repository
public interface Military_unitsRepository extends JpaRepository<Military_units, Integer> { }

