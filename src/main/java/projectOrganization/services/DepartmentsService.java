package projectOrganization.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import projectOrganization.dto.DepartmentsDTO;
import projectOrganization.entity.Departments;
import projectOrganization.entity.Platoons;
import projectOrganization.repository.DepartmentsRepository;
import projectOrganization.repository.PlatoonsRepository;

import java.util.List;

@Service
public class DepartmentsService {
    @Autowired
    DepartmentsRepository departmentsRepository;
    @Autowired
    PlatoonsRepository platoonsRepository;

    public List<Departments> getAllDepartments() throws Exception {
        return (List<Departments>) departmentsRepository.findAll();
    }

    public Departments getDepartments(Integer id_department) {
        return departmentsRepository.findById(id_department).get();
    }

    public void addDepartments(DepartmentsDTO request) {
        Departments department = new Departments();

        department.setId_department(request.getId_department());
        Platoons platoons = platoonsRepository.findById(request.getId_platoon()).get();
        department.setPlatoons(platoons);
        departmentsRepository.save(department);
    }

    public void deleteDepartments(Integer id_department) {
        Departments department = departmentsRepository.findById(id_department).get();
        departmentsRepository.delete(department);
    }

    public ResponseEntity<String> editDepartments(@RequestBody DepartmentsDTO departmentsDTO) {
        try {
            if(!departmentsRepository.existsById(departmentsDTO.getId_department())) {
                return ResponseEntity.badRequest().body("Отдела не существует");
            }

            Departments department = departmentsRepository.findById(departmentsDTO.getId_department()).get();

            department.setId_department(departmentsDTO.getId_department());
            Platoons platoons = platoonsRepository.findById(departmentsDTO.getId_platoon()).get();
            department.setPlatoons(platoons);
            departmentsRepository.save(department);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
