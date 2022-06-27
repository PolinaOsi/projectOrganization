package projectOrganization.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.entity.Dislocations;
import projectOrganization.entity.Military_units;
import projectOrganization.models.DislocationsModel;
import projectOrganization.repository.DislocationsRepository;
import projectOrganization.repository.Military_unitsRepository;
import projectOrganization.services.DislocationsService;

import java.util.ArrayList;
import java.util.List;

    @Controller
    @RequestMapping(path = "/dislocations")
    public class DislocationsController {
        @Autowired
        private DislocationsService dislocationsService;
        @Autowired
        private DislocationsRepository dislocationsRepository;
        @Autowired
        private Military_unitsRepository military_unitsRepository;

        @GetMapping("/all")
        public ResponseEntity<?> getAllDislocations() {
            try {
                List<DislocationsModel> dislocationsModelList = new ArrayList<>();
                dislocationsService.getAllDislocations().forEach(dislocation -> dislocationsModelList.add(DislocationsModel.toModel(dislocation)));
                return ResponseEntity.ok(dislocationsModelList);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @GetMapping("/{id_dislocation}")
        public ResponseEntity<?> getDislocations (@PathVariable Integer id_dislocation) {
            try {
                return ResponseEntity.ok(dislocationsService.getDislocations(id_dislocation));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("/{id_dislocation}")
        public ResponseEntity<?> deleteDislocations(@PathVariable Integer id_dislocation) {
            try {
                dislocationsService.deleteDislocations(id_dislocation);
                return ResponseEntity.ok("Дислокация удалена");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PostMapping("/add")
        public ResponseEntity<?> addDislocations(@RequestBody DislocationsDTO request ) {
            try {
                dislocationsService.addDislocations(request);
                return ResponseEntity.ok("Дислокация добавлена");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PostMapping("/edit")
        public ResponseEntity<?>editDislocations(@RequestBody DislocationsDTO request) {
            try {
                dislocationsService.editDislocations(request);
                return ResponseEntity.ok("Дислокация изменена");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PutMapping("{id_dislocation}/units/{id_unit}")
        public ResponseEntity<String> addUnitsToDislocation(@PathVariable Integer id_dislocation, @PathVariable Integer id_unit) {
            try {
                if(!dislocationsRepository.existsById(id_dislocation)) {
                    return ResponseEntity.badRequest().body("Дислокации не существует");
                }
                if(!military_unitsRepository.existsById(id_unit)) {
                    return ResponseEntity.badRequest().body("Военной части не существует");
                }

                Dislocations dislocation = dislocationsRepository.findById(id_dislocation).get();
                Military_units military_unit = military_unitsRepository.findById(id_unit).get();

                dislocation.addMilitaryUnits(military_unit);

                dislocationsRepository.save(dislocation);

                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
}
