package projectOrganization.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectOrganization.entity.Constructions;

@Repository
public interface ConstructionsRepository extends JpaRepository<Constructions, Integer> { }
