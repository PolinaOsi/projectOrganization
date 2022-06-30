package projectOrganization.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import projectOrganization.dto.CompaniesDTO;
import projectOrganization.entity.Companies;
import projectOrganization.entity.Military_units;
import projectOrganization.repository.CompaniesRepository;
import projectOrganization.repository.Military_unitsRepository;

import java.util.List;

@Service
public class CompaniesService {
    @Autowired
    CompaniesRepository companiesRepository;
    @Autowired
    Military_unitsRepository military_unitsRepository;

    public List<Companies> getAllCompanies() throws Exception {
        return (List<Companies>) companiesRepository.findAll();
    }

    public Companies getCompanies(Integer id_company) {
        return companiesRepository.findById(id_company).get();
    }

    public void addCompanies(CompaniesDTO request) {
        Companies company = new Companies();
        company.setId_company(request.getId_company());
        Military_units military_unit = military_unitsRepository.findById(request.getId_unit()).get();
        company.setMilitary_units(military_unit);

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

            Companies company = companiesRepository.findById(companiesDTO.getId_company()).get();

            company.setId_company(companiesDTO.getId_company());
            Military_units military_unit = military_unitsRepository.findById(companiesDTO.getId_unit()).get();
            company.setMilitary_units(military_unit);
            companiesRepository.save(company);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
