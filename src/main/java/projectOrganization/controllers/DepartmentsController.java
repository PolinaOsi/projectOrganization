package projectOrganization.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.entity.Companies;
import projectOrganization.entity.Departments;
import projectOrganization.repository.CompaniesRepository;
import projectOrganization.repository.DepartmentsRepository;

import java.util.List;

@Controller
@RequestMapping(path = "/departments")
public class DepartmentsController {

        @Autowired
        private DepartmentsRepository departmentsRepository;

        private final ModelMapper modelMapper = new ModelMapper();

        @GetMapping("/all")
        public ResponseEntity<List<DepartmentsOutDTO>> getAllDepartments() {
            try {
                List<Departments> result = departmentsRepository.findAll();
                List<DepartmentsOutDTO> departmentsOutDTO = modelMapper.map(result, new TypeToken<List<DepartmentsOutDTO>>() {
                }.getType());
                return ResponseEntity.ok(departmentsOutDTO);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<DepartmentsOutDTO> getDepartments(@PathVariable Integer id) {
            try {
                if (departmentsRepository.existsById(id)) {
                    Departments result = departmentsRepository.findById(id).get();
                    DepartmentsOutDTO departmentsOutDTO = modelMapper.map(result, DepartmentsOutDTO.class);
                    return ResponseEntity.ok(departmentsOutDTO);
                }
                return ResponseEntity.badRequest().body(null);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteDepartments(@PathVariable Integer id) {
            try {
                if (!departmentsRepository.existsById(id)) {
                    return ResponseEntity.badRequest().body("Отделения не существует");
                }
                departmentsRepository.deleteById(id);
                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(" ");
            }
        }

        @PostMapping("/add")
        public ResponseEntity<String> addDepartments(@RequestBody DepartmentsDTO departmentsDTO ) {
            try {
                departmentsDTO.setId_department(null);

                Departments department = new Departments(null, departmentsDTO.getId_platoon());

                departmentsRepository.save(department);

                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Ошибка");
            }
        }

        @PostMapping("/edit")
        public ResponseEntity<String> editDepartments(@RequestBody DepartmentsDTO departmentsDTO) {
            try {
                if(!departmentsRepository.existsById(departmentsDTO.getId_department())) {
                    return ResponseEntity.badRequest().body("Отделения не существует");
                }

                Departments department = new Departments(departmentsDTO.getId_department(),
                        departmentsDTO.getId_platoon());
                departmentsRepository.save(department);

                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Ошибка");
            }
        }
}
