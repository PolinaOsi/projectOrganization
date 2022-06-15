package projectOrganization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectOrganization.entity.Dislocations;

@Repository
public interface DislocationsRepository extends JpaRepository<Dislocations, Integer> { }

