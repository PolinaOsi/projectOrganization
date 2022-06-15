package projectOrganization.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.entity.Armaments;
import projectOrganization.entity.Military_units;
import projectOrganization.repository.ArmamentsRepository;
import projectOrganization.repository.Military_unitsRepository;

import java.util.List;

@Controller
@RequestMapping(path = "/military_units")
public class Military_unitsController {
    @Autowired
    private Military_unitsRepository military_unitsRepository;
    @Autowired
    private ArmamentsRepository armamentsRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/all")
    public ResponseEntity<List<Military_unitsOutDTO>> getAllMilitaryUnits() {
        try {
            List<Military_units> result = military_unitsRepository.findAll();
            List<Military_unitsOutDTO> military_unitsOutDTO = modelMapper.map(result, new TypeToken<List<Military_unitsOutDTO>>() {
            }.getType());
            return ResponseEntity.ok(military_unitsOutDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Military_unitsOutDTO> getMilitaryUnits(@PathVariable Integer id) {
        try {
            if (military_unitsRepository.existsById(id)) {
                Military_units result = military_unitsRepository.findById(id).get();
                Military_unitsOutDTO military_unitsOutDTO = modelMapper.map(result, Military_unitsOutDTO.class);
                return ResponseEntity.ok(military_unitsOutDTO);
            }
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMilitaryUnits(@PathVariable Integer id) {
        try {
            if (!military_unitsRepository.existsById(id)) {
                return ResponseEntity.badRequest().body("Военной части не существует");
            }
            military_unitsRepository.deleteById(id);
            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
        return ResponseEntity.badRequest().body(" ");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMilitaryUnits(@RequestBody Military_unitsDTO military_unitsDTO ) {
    try {
        military_unitsDTO.setId_unit(null);

        Military_units military_unit = new Military_units(null, military_unitsDTO.getName_unit(),
                military_unitsDTO.getId_association(),
                military_unitsDTO.getId_dislocation());

        military_unitsRepository.save(military_unit);

        return ResponseEntity.ok("Успех");
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<String> editMilitaryUnit(@RequestBody Military_unitsDTO military_unitsDTO) {
    try {
        if(!military_unitsRepository.existsById(military_unitsDTO.getId_unit())) {
            return ResponseEntity.badRequest().body("Военной части не существует");
        }

        Military_units military_unit = new Military_units(military_unitsDTO.getId_unit(),
                military_unitsDTO.getName_unit(),
                military_unitsDTO.getId_association(),
                military_unitsDTO.getId_dislocation());
        military_unitsRepository.save(military_unit);

        return ResponseEntity.ok("Успех");
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PutMapping("{id_unit}/armaments/{id_armament}")
    public ResponseEntity<String> addArmamentsToUnit(@PathVariable Integer id_unit, @PathVariable Integer id_armament) {
        try {
            if(!armamentsRepository.existsById(id_armament)) {
                return ResponseEntity.badRequest().body("Вооружения не существует");
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
            return ResponseEntity.badRequest().body("ошибка");
        }
    }

}