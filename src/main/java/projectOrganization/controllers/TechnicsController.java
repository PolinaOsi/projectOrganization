
package projectOrganization.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.models.TechnicsModel;
import projectOrganization.services.TechnicsService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/technics")
public class TechnicsController {
        @Autowired
        private TechnicsService technicsService;

        @GetMapping("/all")
        public ResponseEntity<?> getAllTechnics() {
            try {
                List<TechnicsModel> technicsModelList = new ArrayList<>();
                technicsService.getAllTechnics().forEach(technic -> technicsModelList.add(TechnicsModel.toModel(technic)));
                return ResponseEntity.ok(technicsModelList);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @GetMapping("/{id_technic}")
        public ResponseEntity<?> getTechnics(@PathVariable Integer id_technic) {
            try {
                return ResponseEntity.ok(technicsService.getTechnics(id_technic));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("/{id_technic}")
        public ResponseEntity<?> deleteTechnics(@PathVariable Integer id_technic) {
            try {
                technicsService.deleteTechnics(id_technic);
                return ResponseEntity.ok("Техника удалена");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PostMapping("/add")
        public ResponseEntity<?> addTechnics(@RequestBody TechnicsDTO request ) {
            try {
                technicsService.addTechnics(request);
                return ResponseEntity.ok("Техника добавлена");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PostMapping("/edit")
        public ResponseEntity<?> editTechnics(@RequestBody TechnicsDTO request) {
            try {
                technicsService.editTechnics(request);
                return ResponseEntity.ok("Техника изменена");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
}

