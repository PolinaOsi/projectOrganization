package projectOrganization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectOrganization.entity.Companies;

@Repository
public interface CompaniesRepository extends JpaRepository<Companies, Integer> { }