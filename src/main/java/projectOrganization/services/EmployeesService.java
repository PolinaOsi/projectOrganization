package projectOrganization.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import projectOrganization.dto.EmployeesDTO;
import projectOrganization.dto.Military_unitsDTO;
import projectOrganization.entity.*;
import projectOrganization.repository.*;

import java.util.List;

@Service
public class EmployeesService {
    @Autowired
    EmployeesRepository employeesRepository;
    @Autowired
    AssociationsRepository associationsRepository;
    @Autowired
    RanksRepository ranksRepository;
    @Autowired
    ArmiesRepository armiesRepository;
    @Autowired
    Military_unitsRepository military_unitsRepository;
    @Autowired
    DepartmentsRepository departmentsRepository;

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
        Employees boss = employeesRepository.findById(request.getId_employee()).get();
        employee.setEmployee(boss);
        employee.setDate_birth(request.getDate_birth());
        Associations association = associationsRepository.findById(request.getId_association()).get();
        employee.setAssociation(association);
        Ranks ranks = ranksRepository.findById(request.getId_rank()).get();
        employee.setRanks(ranks);
        Armies army = armiesRepository.findById(request.getId_rank()).get();
        employee.setArmy(army);
        Military_units military_units = military_unitsRepository.findById(request.getId_unit()).get();
        employee.setMilitary_unit(military_units);
        Departments departments = departmentsRepository.findById(request.getId_department()).get();
        employee.setDepartment(departments);
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

            Employees employee = employeesRepository.findById(employeesDTO.getId_employee()).get();

            employee.setId_employee(employeesDTO.getId_employee());
            employee.setName_employee(employeesDTO.getName_employee());
            employee.setSurname_employee(employeesDTO.getSurname_employee());
            employee.setPatronymic_employee(employeesDTO.getPatronymic_employee());
            Employees boss = employeesRepository.findById(employeesDTO.getId_employee()).get();
            employee.setEmployee(boss);
            employee.setDate_birth(employeesDTO.getDate_birth());
            Associations association = associationsRepository.findById(employeesDTO.getId_association()).get();
            employee.setAssociation(association);
            Ranks ranks = ranksRepository.findById(employeesDTO.getId_rank()).get();
            employee.setRanks(ranks);
            Armies army = armiesRepository.findById(employeesDTO.getId_rank()).get();
            employee.setArmy(army);
            Military_units military_units = military_unitsRepository.findById(employeesDTO.getId_unit()).get();
            employee.setMilitary_unit(military_units);
            Departments departments = departmentsRepository.findById(employeesDTO.getId_department()).get();
            employee.setDepartment(departments);
            employee.setCharacteristic(employeesDTO.getCharacteristic());
            employeesRepository.save(employee);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}


