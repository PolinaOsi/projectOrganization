package projectOrganization.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.entity.Employees;
import projectOrganization.repository.EmployeesRepository;

import java.util.List;

@Controller
@RequestMapping(path = "/employees")
public class EmployeesController {
        @Autowired
        private EmployeesRepository employeesRepository;

        private final ModelMapper modelMapper = new ModelMapper();

        @GetMapping("/all")
        public ResponseEntity<List<EmployeesOutDTO>> getAllEmployees() {
            try {
                List<Employees> result = employeesRepository.findAll();
                List<EmployeesOutDTO> employeesOutDTO = modelMapper.map(result, new TypeToken<List<EmployeesOutDTO>>() {
                }.getType());
                return ResponseEntity.ok(employeesOutDTO);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<EmployeesOutDTO> getEmployees (@PathVariable Integer id) {
            try {
                if (employeesRepository.existsById(id)) {
                    Employees result = employeesRepository.findById(id).get();
                    EmployeesOutDTO employeesOutDTO = modelMapper.map(result, EmployeesOutDTO.class);
                    return ResponseEntity.ok(employeesOutDTO);
                }
                return ResponseEntity.badRequest().body(null);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteEmployees(@PathVariable Integer id) {
            try {
                if (!employeesRepository.existsById(id)) {
                    return ResponseEntity.badRequest().body("Сотрудника не существует");
                }
                employeesRepository.deleteById(id);
                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(" ");
            }
        }

        @PostMapping("/add")
        public ResponseEntity<String> addEmployees(@RequestBody EmployeesDTO employeesDTO ) {
            try {
                employeesDTO.setId_employee(null);

                Employees employee = new Employees(null, employeesDTO.getName_employee(), employeesDTO.getSurname_employee(),
                        employeesDTO.getPatronymic_employee(), employeesDTO.getDate_birth(), employeesDTO.getId_association(),
                        employeesDTO.getId_rank(), employeesDTO.getId_army(), employeesDTO.getId_unit(), employeesDTO.getId_department(),
                        employeesDTO.getCharacteristic());

                employeesRepository.save(employee);

                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Ошибка");
            }
        }

        @PostMapping("/edit")
        public ResponseEntity<String> editEmployees(@RequestBody EmployeesDTO employeesDTO) {
            try {
                if(!employeesRepository.existsById(employeesDTO.getId_employee())) {
                    return ResponseEntity.badRequest().body("Сотрудника не существует");
                }

                Employees employee = new Employees(employeesDTO.getId_employee(), employeesDTO.getName_employee(), employeesDTO.getSurname_employee(),
                        employeesDTO.getPatronymic_employee(), employeesDTO.getDate_birth(), employeesDTO.getId_association(),
                        employeesDTO.getId_rank(), employeesDTO.getId_army(), employeesDTO.getId_unit(), employeesDTO.getId_department(),
                        employeesDTO.getCharacteristic());
                employeesRepository.save(employee);

                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Ошибка");
            }
        }

}
