package projectOrganization.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.entity.Armaments;
import projectOrganization.entity.Dislocations;
import projectOrganization.entity.Military_units;
import projectOrganization.entity.Technics;
import projectOrganization.models.Military_unitsModel;
import projectOrganization.repository.ArmamentsRepository;
import projectOrganization.repository.DislocationsRepository;
import projectOrganization.repository.Military_unitsRepository;
import projectOrganization.repository.TechnicsRepository;
import projectOrganization.services.Military_unitsService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/military_units")
public class Military_unitsController {
    @Autowired
    private Military_unitsRepository military_unitsRepository;
    @Autowired
    private ArmamentsRepository armamentsRepository;
    @Autowired
    private Military_unitsService military_unitsService;
    @Autowired
    private TechnicsRepository technicsRepository;
    @Autowired
    private DislocationsRepository dislocationsRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllMilitaryUnits() {
        try {
            List<Military_unitsModel> military_unitsModelList = new ArrayList<>();
            military_unitsService.getAllMilitaryUnits().forEach(military_unit -> military_unitsModelList.add(Military_unitsModel.toModel(military_unit)));
            return ResponseEntity.ok(military_unitsModelList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id_unit}")
    public ResponseEntity<?> getMilitaryUnits(@PathVariable Integer id_unit) {
        try {
            return ResponseEntity.ok(military_unitsService.getMilitaryUnits(id_unit));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id_unit}")
    public ResponseEntity<?> deleteMilitaryUnits(@PathVariable Integer id_unit) {
        try {
            military_unitsService.deleteMilitaryUnits(id_unit);
            return ResponseEntity.ok("Военная чать удалена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMilitaryUnits(@RequestBody Military_unitsDTO request ) {
    try {
        military_unitsService.addMilitaryUnits(request);
        return ResponseEntity.ok("Военная часть добавлена");
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<?>  editMilitaryUnit(@RequestBody Military_unitsDTO request) {
    try {
        military_unitsService.editMilitaryUnit(request);
        return ResponseEntity.ok("Военная часть изменена");
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id_unit}/armaments/{id_armament}")
    public ResponseEntity<String> addArmamentsToUnit(@PathVariable Integer id_unit, @PathVariable Integer id_armament) {
        try {
            if(!armamentsRepository.existsById(id_armament)) {
                return ResponseEntity.badRequest().body("Вооружение не существует");
            }
            if(!military_unitsRepository.existsById(id_unit)) {
                return ResponseEntity.badRequest().body("Военной части не существует");
            }

            Armaments armament = armamentsRepository.findById(id_armament).get();
            Military_units military_unit = military_unitsRepository.findById(id_unit).get();

            military_unit.addArmament(armament);

            military_unitsRepository.save(military_unit);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id_unit}/technics/{id_technic}")
    public ResponseEntity<String> addTechnicsToUnit(@PathVariable Integer id_unit, @PathVariable Integer id_technic) {
        try {
            if(!technicsRepository.existsById(id_technic)) {
                return ResponseEntity.badRequest().body("Техники не существует");
            }
            if(!military_unitsRepository.existsById(id_unit)) {
                return ResponseEntity.badRequest().body("Военной части не существует");
            }

            Technics technic = technicsRepository.findById(id_technic).get();
            Military_units military_unit = military_unitsRepository.findById(id_unit).get();

            military_unit.addTechnic(technic);

            military_unitsRepository.save(military_unit);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id_unit}/dislocations/{id_dislocation}")
    public ResponseEntity<String> addDislocationToUnits(@PathVariable Integer id_unit, @PathVariable Integer id_dislocation) {
        try {
            if(!dislocationsRepository.existsById(id_dislocation)) {
                return ResponseEntity.badRequest().body("Дислокации не существует");
            }
            if(!military_unitsRepository.existsById(id_unit)) {
                return ResponseEntity.badRequest().body("Военной части не существует");
            }

            Dislocations dislocation = dislocationsRepository.findById(id_dislocation).get();
            Military_units military_unit = military_unitsRepository.findById(id_unit).get();

            military_unit.addDislocation(dislocation);

            military_unitsRepository.save(military_unit);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}