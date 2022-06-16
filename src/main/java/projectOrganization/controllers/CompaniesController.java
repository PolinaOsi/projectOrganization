package projectOrganization.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.models.CompaniesModel;
import projectOrganization.services.CompaniesService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/companies")
public class CompaniesController {

        @Autowired
        private CompaniesService companiesService;

        @GetMapping("/all")
        public ResponseEntity<?> getAllCompanies() {
            try {
                List<CompaniesModel> companiesModelList = new ArrayList<>();
                companiesService.getAllCompanies().forEach(company -> companiesModelList.add(CompaniesModel.toModel(company)));
                return ResponseEntity.ok(companiesModelList);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @GetMapping("/{id_company}")
        public ResponseEntity<?> getCompanies(@PathVariable Integer id_company) {
            try {
                return ResponseEntity.ok( companiesService.getCompanies(id_company));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("/{id_company}")
        public ResponseEntity<?> deleteCompanies(@PathVariable Integer id_company) {
            try {
                companiesService.deleteCompanies(id_company);
                return ResponseEntity.ok("Рота удалена");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PostMapping("/add")
        public ResponseEntity<?>addCompanies(@RequestBody CompaniesDTO request ) {
            try {
                companiesService.addCompanies(request);
                return ResponseEntity.ok("Рота добавлена");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PostMapping("/edit")
        public ResponseEntity<?> editCompanies(@RequestBody CompaniesDTO request) {
            try {
                companiesService.editCompanies(request);
                return ResponseEntity.ok("Рота изменена");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
}
