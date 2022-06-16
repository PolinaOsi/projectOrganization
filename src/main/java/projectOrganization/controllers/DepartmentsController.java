package projectOrganization.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.models.DepartmentsModel;
import projectOrganization.services.DepartmentsService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/departments")
public class DepartmentsController {

        @Autowired
        private DepartmentsService departmentsService;

        @GetMapping("/all")
        public ResponseEntity<?> getAllDepartments() {
            try {
                List<DepartmentsModel> departmentsModelList = new ArrayList<>();
                departmentsService.getAllDepartments().forEach(department -> departmentsModelList.add(DepartmentsModel.toModel(department)));
                return ResponseEntity.ok(departmentsModelList);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @GetMapping("/{id_department}")
        public ResponseEntity<?> getDepartments(@PathVariable Integer id_department) {
            try {
                return ResponseEntity.ok(departmentsService.getDepartments(id_department));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("/{id_department}")
        public ResponseEntity<?> deleteDepartments(@PathVariable Integer id_department) {
            try {
                departmentsService.deleteDepartments(id_department);
                return ResponseEntity.ok("Отдел удален");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PostMapping("/add")
        public ResponseEntity<?>addDepartments(@RequestBody DepartmentsDTO request ) {
            try {
                departmentsService.addDepartments(request);
                return ResponseEntity.ok("Отдел добавлен");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PostMapping("/edit")
        public ResponseEntity<?> editDepartments(@RequestBody DepartmentsDTO request) {
            try {
                departmentsService.editDepartments(request);
                return ResponseEntity.ok("Отдел изменен");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
}
