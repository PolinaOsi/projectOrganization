package projectOrganization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectOrganization.entity.Platoons;

@Repository
public interface PlatoonsRepository extends JpaRepository<Platoons, Integer> { }

