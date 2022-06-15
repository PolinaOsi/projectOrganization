package projectOrganization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectOrganization.entity.Armaments;

@Repository
public interface ArmamentsRepository extends JpaRepository<Armaments, Integer> { }


