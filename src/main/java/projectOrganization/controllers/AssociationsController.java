package projectOrganization.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.models.ArmiesModel;
import projectOrganization.models.AssociationsModel;
import projectOrganization.services.AssociationsService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/associations")
public class AssociationsController {

        @Autowired
        private AssociationsService associationsService;

        @GetMapping("/all")
        public ResponseEntity<?> getAllAssociations() {
            try {
                List<AssociationsModel> associationsModelList = new ArrayList<>();
                associationsService.getAllAssociations().forEach(association -> associationsModelList.add(AssociationsModel.toModel(association)));
                return ResponseEntity.ok(associationsModelList);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @GetMapping("/{id_association}")
        public ResponseEntity<?> getAssociations (@PathVariable Integer id_association) {
            try {
                return ResponseEntity.ok(associationsService.getAssociations(id_association));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("/{id_association}")
        public ResponseEntity<?> deleteAssociations(@PathVariable Integer id_association) {
            try {
                associationsService.deleteAssociations(id_association);
                return ResponseEntity.ok("Объединение удалено");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

    @PostMapping("/add")
    public ResponseEntity<?> addAssociations(@RequestBody AssociationsDTO request) {
        try {
            associationsService.addAssociations(request);
            return ResponseEntity.ok("Оъединение добавлено");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editAssociations(@RequestBody AssociationsDTO request) {
        try {
            associationsService.editAssociations(request);
            return ResponseEntity.ok("Оъединение изменено");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

