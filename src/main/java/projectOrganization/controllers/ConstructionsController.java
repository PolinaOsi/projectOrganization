package projectOrganization.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.models.ArmiesModel;
import projectOrganization.models.AssociationsModel;
import projectOrganization.models.ConstructionsModel;
import projectOrganization.services.AssociationsService;
import projectOrganization.services.ConstructionsService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/constructions")
public class ConstructionsController {
        @Autowired
        private ConstructionsService constructionsService;

        @GetMapping("/all")
        public ResponseEntity<?> getAllConstructions() {
            try {
                List<ConstructionsModel> constructionsModelList = new ArrayList<>();
                constructionsService.getAllConstructions().forEach(construction -> constructionsModelList.add(ConstructionsModel.toModel(construction)));
                return ResponseEntity.ok(constructionsModelList);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @GetMapping("/{id_construction}")
        public ResponseEntity<?> getConstructions (@PathVariable Integer id_construction) {
            try {
                return ResponseEntity.ok(constructionsService.getConstructions(id_construction));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("/{id_construction}")
        public ResponseEntity<?> deleteConstructions(@PathVariable Integer id_construction) {
            try {
                constructionsService.deleteConstructions(id_construction);
                return ResponseEntity.ok("Сооружение удалено");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PostMapping("/add")
        public ResponseEntity<?> addConstructions(@RequestBody ConstructionsDTO request) {
            try {
                constructionsService.addConstructions(request);
                return ResponseEntity.ok("Сооружение добавлено");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PostMapping("/edit")
        public ResponseEntity<?> editConstructions(@RequestBody ConstructionsDTO request) {
            try {
                constructionsService.editConstructions(request);
                return ResponseEntity.ok("Сооружение изменено");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

}
