package projectOrganization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectOrganization.entity.Armies;

@Repository
public interface ArmiesRepository extends JpaRepository<Armies, Integer> { }


