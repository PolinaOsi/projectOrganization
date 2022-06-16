package projectOrganization.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import projectOrganization.dto.CompaniesDTO;
import projectOrganization.entity.Companies;
import projectOrganization.repository.CompaniesRepository;

import java.util.List;

@Service
public class CompaniesService {
    @Autowired
    CompaniesRepository companiesRepository;

    public List<Companies> getAllCompanies() throws Exception {
        return (List<Companies>) companiesRepository.findAll();
    }

    public Companies getCompanies(Integer id_company) {
        return companiesRepository.findById(id_company).get();
    }

    public void addCompanies(CompaniesDTO request) {
        Companies company = new Companies();
        company.setId_company(request.getId_company());
        company.setId_unit(request.getId_unit());

        companiesRepository.save(company);
    }

    public void deleteCompanies(Integer id_company) {
        Companies company = companiesRepository.findById(id_company).get();
        companiesRepository.delete(company);
    }

    public ResponseEntity<String> editCompanies(@RequestBody CompaniesDTO companiesDTO) {
        try {
            if(!companiesRepository.existsById(companiesDTO.getId_company())) {
                return ResponseEntity.badRequest().body("Роты не существует");
            }

            Companies company = new Companies(companiesDTO.getId_company(), companiesDTO.getId_unit());
            companiesRepository.save(company);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
