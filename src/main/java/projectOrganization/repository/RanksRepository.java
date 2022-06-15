package projectOrganization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectOrganization.entity.Ranks;

@Repository
public interface RanksRepository extends JpaRepository<Ranks, Integer> { }

