package projectOrganization.repository;

        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;
        import projectOrganization.entity.Employees;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer> { }

