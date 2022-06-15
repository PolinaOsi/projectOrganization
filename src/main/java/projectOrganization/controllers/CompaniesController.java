package projectOrganization.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.entity.Companies;
import projectOrganization.repository.CompaniesRepository;

import java.util.List;

@Controller
@RequestMapping(path = "/companies")
public class CompaniesController {

        @Autowired
        private CompaniesRepository companiesRepository;

        private final ModelMapper modelMapper = new ModelMapper();

        @GetMapping("/all")
        public ResponseEntity<List<CompaniesOutDTO>> getAllCompanies() {
            try {
                List<Companies> result = companiesRepository.findAll();
                List<CompaniesOutDTO> companiesOutDTO = modelMapper.map(result, new TypeToken<List<CompaniesOutDTO>>() {
                }.getType());
                return ResponseEntity.ok(companiesOutDTO);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<CompaniesOutDTO> getCompanies(@PathVariable Integer id) {
            try {
                if (companiesRepository.existsById(id)) {
                    Companies result = companiesRepository.findById(id).get();
                    CompaniesOutDTO companiesOutDTO = modelMapper.map(result, CompaniesOutDTO.class);
                    return ResponseEntity.ok(companiesOutDTO);
                }
                return ResponseEntity.badRequest().body(null);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteCompanies(@PathVariable Integer id) {
            try {
                if (!companiesRepository.existsById(id)) {
                    return ResponseEntity.badRequest().body("Роты не существует");
                }
                companiesRepository.deleteById(id);
                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(" ");
            }
        }

        @PostMapping("/add")
        public ResponseEntity<String> addCompanies(@RequestBody CompaniesDTO companiesDTO ) {
            try {
                companiesDTO.setId_company(null);

                Companies company = new Companies(null, companiesDTO.getId_unit());

                companiesRepository.save(company);

                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Ошибка");
            }
        }

        @PostMapping("/edit")
        public ResponseEntity<String> editCompanies(@RequestBody CompaniesDTO companiesDTO) {
            try {
                if(!companiesRepository.existsById(companiesDTO.getId_company())) {
                    return ResponseEntity.badRequest().body("Роты не существует");
                }

                Companies company = new Companies(companiesDTO.getId_company(),
                        companiesDTO.getId_unit());
                companiesRepository.save(company);

                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Ошибка");
            }
        }
    }
