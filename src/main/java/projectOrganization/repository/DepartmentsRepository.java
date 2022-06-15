package projectOrganization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectOrganization.entity.Departments;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments, Integer> { }

