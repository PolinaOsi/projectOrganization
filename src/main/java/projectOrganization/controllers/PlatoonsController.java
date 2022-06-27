package projectOrganization.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.models.PlatoonsModel;
import projectOrganization.services.PlatoonsService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/platoons")
public class PlatoonsController {
        @Autowired
        private PlatoonsService platoonsService;

        @GetMapping("/all")
        public ResponseEntity<?> getAllPlatoons() {
            try {
                List<PlatoonsModel> platoonsModelList = new ArrayList<>();
                platoonsService.getAllPlatoons().forEach(platoon -> platoonsModelList.add(PlatoonsModel.toModel(platoon)));
                return ResponseEntity.ok(platoonsModelList);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @GetMapping("/{id_platoon}")
        public ResponseEntity<?> getPlatoons(@PathVariable Integer id_platoon) {
            try {
                return ResponseEntity.ok(platoonsService.getPlatoons(id_platoon));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("/{id_platoon}")
        public ResponseEntity<?> deletePlatoons(@PathVariable Integer id_platoon) {
            try {
                platoonsService.deletePlatoons(id_platoon);
                return ResponseEntity.ok("Взвод удален");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PostMapping("/add")
        public ResponseEntity<?> addPlatoons(@RequestBody PlatoonsDTO request ) {
            try {
                platoonsService.addPlatoons(request);
                return ResponseEntity.ok("Взвод добавлен");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PostMapping("/edit")
        public ResponseEntity<?> editPlatoons(@RequestBody PlatoonsDTO request) {
            try {
                platoonsService.editPlatoons(request);
                return ResponseEntity.ok("Взвод изменен");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
}

