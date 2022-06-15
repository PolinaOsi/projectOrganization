package projectOrganization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectOrganization.entity.Associations;

@Repository
public interface AssociationsRepository extends JpaRepository<Associations, Integer> { }


