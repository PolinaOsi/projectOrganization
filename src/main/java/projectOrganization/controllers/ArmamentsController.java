package projectOrganization.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectOrganization.models.ArmamentsModel;
import projectOrganization.dto.ArmamentsDTO;
import projectOrganization.services.ArmamentsService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/armaments")
public class ArmamentsController {
        @Autowired
        private ArmamentsService armamentsService;

        @GetMapping("/all")
        public ResponseEntity<?> getAllArmaments() {
                try {
                        List<ArmamentsModel> armamentsModelList = new ArrayList<>();
                        armamentsService.getAllArmaments().forEach(armament -> armamentsModelList.add(ArmamentsModel.toModel(armament)));
                        return ResponseEntity.ok(armamentsModelList);
                } catch (Exception e) {
                        return ResponseEntity.badRequest().body(e.getMessage());
                }
        }

        @GetMapping("/{id_armament}")
        public ResponseEntity<?> getArmaments(@PathVariable Integer id_armament) {
                try {
                        return ResponseEntity.ok(armamentsService.getArmaments(id_armament));
                } catch (Exception e) {
                        return ResponseEntity.badRequest().body(e.getMessage());
                }
        }

        @PostMapping("/add")
        public ResponseEntity<?> addArmaments(@RequestBody ArmamentsDTO request) {
                try {
                        armamentsService.addArmaments(request);
                        return ResponseEntity.ok("Вооружение добавлено");
                } catch (Exception e) {
                        return ResponseEntity.badRequest().body(e.getMessage());
                }
        }

        @DeleteMapping("/{id_armament}")
        public ResponseEntity<?> deleteArmaments(@RequestBody Integer id_armament) {
                try {
                        armamentsService.deleteArmaments(id_armament);
                        return ResponseEntity.ok("Вооружение удалено");
                } catch (Exception e) {
                        return ResponseEntity.badRequest().body(e.getMessage());
                }
        }

        @PutMapping("/edit")
        public ResponseEntity<?> editArmaments(@RequestBody ArmamentsDTO request) {
                try {
                        armamentsService.editArmaments(request);
                        return ResponseEntity.ok("Вооружение изменено");
                } catch (Exception e) {
                        return ResponseEntity.badRequest().body(e.getMessage());
                }
        }
}