package projectOrganization.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import projectOrganization.dto.EmployeesDTO;
import projectOrganization.entity.Employees;
import projectOrganization.repository.EmployeesRepository;

import java.util.List;

@Service
public class EmployeesService {
    @Autowired
    EmployeesRepository employeesRepository;

    public List<Employees> getAllEmployees() throws Exception {
        return (List<Employees>) employeesRepository.findAll();
    }

    public Employees getEmployees(Integer id_employee) {
        return employeesRepository.findById(id_employee).get();
    }

    public void addEmployees(EmployeesDTO request) {
        Employees employee = new Employees();
        employee.setId_employee(request.getId_employee());
        employee.setName_employee(request.getName_employee());
        employee.setSurname_employee(request.getSurname_employee());
        employee.setPatronymic_employee(request.getPatronymic_employee());
        employee.setDate_birth(request.getDate_birth());
        employee.setId_association(request.getId_association());
        employee.setId_rank(request.getId_rank());
        employee.setId_army(request.getId_army());
        employee.setId_unit(request.getId_unit());
        employee.setId_department(request.getId_department());
        employee.setCharacteristic(request.getCharacteristic());
        employeesRepository.save(employee);
    }

    public void deleteEmployees(Integer id_employee) {
        Employees employee = employeesRepository.findById(id_employee).get();
        employeesRepository.delete(employee);
    }

    public ResponseEntity<String> editEmployees(@RequestBody EmployeesDTO employeesDTO) {
        try {
            if(!employeesRepository.existsById(employeesDTO.getId_employee())) {
                return ResponseEntity.badRequest().body("Сотрудника не существует");
            }

            Employees employee = new Employees(employeesDTO.getId_employee(), employeesDTO.getName_employee(),
                    employeesDTO.getSurname_employee(), employeesDTO.getPatronymic_employee(),
                    employeesDTO.getDate_birth(), employeesDTO.getId_association(),
                    employeesDTO.getId_rank(), employeesDTO.getId_army(),
                    employeesDTO.getId_unit(), employeesDTO.getId_department(),
                    employeesDTO.getCharacteristic());
            employeesRepository.save(employee);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}


