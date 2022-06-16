package projectOrganization.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.models.ArmiesModel;
import projectOrganization.services.ArmiesService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/armies")
public class ArmiesController {
    @Autowired
    private ArmiesService armiesService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllArmies() {
        try {
            List<ArmiesModel> armiesModelList = new ArrayList<>();
            armiesService.getAllArmies().forEach(army -> armiesModelList.add(ArmiesModel.toModel(army)));
            return ResponseEntity.ok(armiesModelList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id_army}")
    public ResponseEntity<?> getArmies (@PathVariable Integer id_army) {
        try {
            return ResponseEntity.ok(armiesService.getArmies(id_army));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id_army}")
    public ResponseEntity<?> deleteArmies(@PathVariable Integer id_army) {
        try {
            armiesService.deleteArmies(id_army);
            return ResponseEntity.ok("Армия удалена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addArmies(@RequestBody ArmiesDTO request) {
        try {
            armiesService.addArmies(request);
            return ResponseEntity.ok("Армия добавлена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editArmies(@RequestBody ArmiesDTO request) {
        try {
            armiesService.editArmies(request);
            return ResponseEntity.ok("Армия изменена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
