package projectOrganization.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.entity.Armies;
import projectOrganization.repository.ArmiesRepository;

import java.util.List;

@Controller
@RequestMapping(path = "/armies")
public class ArmiesController {
    @Autowired
    private ArmiesRepository armiesRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/all")
    public ResponseEntity<List<ArmiesOutDTO>> getAllArmies() {
        try {
            List<Armies> result = armiesRepository.findAll();
            List<ArmiesOutDTO> armiesOutDTO = modelMapper.map(result, new TypeToken<List<ArmiesOutDTO>>() { }.getType());
            return ResponseEntity.ok(armiesOutDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArmiesOutDTO> getArmies (@PathVariable Integer id) {
        try {
            if (armiesRepository.existsById(id)) {
                Armies result = armiesRepository.findById(id).get();
                ArmiesOutDTO armiesOutDTO = modelMapper.map(result, ArmiesOutDTO.class);
                return ResponseEntity.ok(armiesOutDTO);
            }
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArmies(@PathVariable Integer id) {
        try {
            if (!armiesRepository.existsById(id)) {
                return ResponseEntity.badRequest().body("Армии не существует");
            }
            armiesRepository.deleteById(id);
            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(" ");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addArmies(@RequestBody ArmiesDTO armiesDTO ) {
        try {
            armiesDTO.setId_army(null);

            Armies armies = new Armies(null, armiesDTO.getName_army());

            armiesRepository.save(armies);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<String> editArmies(@RequestBody ArmiesDTO armiesDTO) {
        try {
            if(!armiesRepository.existsById(armiesDTO.getId_army())) {
                return ResponseEntity.badRequest().body("Армии не существует");
            }

            Armies army = new Armies(armiesDTO.getId_army(), armiesDTO.getName_army());
            armiesRepository.save(army);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
