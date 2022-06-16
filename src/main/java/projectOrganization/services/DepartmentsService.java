package projectOrganization.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import projectOrganization.dto.DepartmentsDTO;
import projectOrganization.entity.Departments;
import projectOrganization.repository.DepartmentsRepository;

import java.util.List;

@Service
public class DepartmentsService {
    @Autowired
    DepartmentsRepository departmentsRepository;

    public List<Departments> getAllDepartments() throws Exception {
        return (List<Departments>) departmentsRepository.findAll();
    }

    public Departments getDepartments(Integer id_department) {
        return departmentsRepository.findById(id_department).get();
    }

    public void addDepartments(DepartmentsDTO request) {
        Departments department = new Departments();

        department.setId_department(request.getId_department());
        department.setId_platoon(request.getId_platoon());
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

            Departments department = new Departments(departmentsDTO.getId_department(), departmentsDTO.getId_platoon());
            departmentsRepository.save(department);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
