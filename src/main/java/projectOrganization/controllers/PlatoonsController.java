package projectOrganization.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.entity.Platoons;
import projectOrganization.repository.PlatoonsRepository;

import java.util.List;

@Controller
@RequestMapping(path = "/platoons")
public class PlatoonsController {
        @Autowired
        private PlatoonsRepository platoonsRepository;

        private final ModelMapper modelMapper = new ModelMapper();

        @GetMapping("/all")
        public ResponseEntity<List<PlatoonsOutDTO>> getAllPlatoons() {
            try {
                List<Platoons> result = platoonsRepository.findAll();
                List<PlatoonsOutDTO> platoonsOutDTO = modelMapper.map(result, new TypeToken<List<PlatoonsOutDTO>>() {
                }.getType());
                return ResponseEntity.ok(platoonsOutDTO);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<PlatoonsOutDTO> getPlatoons(@PathVariable Integer id) {
            try {
                if (platoonsRepository.existsById(id)) {
                    Platoons result = platoonsRepository.findById(id).get();
                    PlatoonsOutDTO platoonsOutDTO = modelMapper.map(result, PlatoonsOutDTO.class);
                    return ResponseEntity.ok(platoonsOutDTO);
                }
                return ResponseEntity.badRequest().body(null);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deletePlatoons(@PathVariable Integer id) {
            try {
                if (!platoonsRepository.existsById(id)) {
                    return ResponseEntity.badRequest().body("Взвода не существует");
                }
                platoonsRepository.deleteById(id);
                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(" ");
            }
        }

        @PostMapping("/add")
        public ResponseEntity<String> addPlatoons(@RequestBody PlatoonsDTO platoonsDTO ) {
            try {
                platoonsDTO.setId_platoon(null);

                Platoons platoon = new Platoons(null, platoonsDTO.getId_company());

                platoonsRepository.save(platoon);

                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Ошибка");
            }
        }

        @PostMapping("/edit")
        public ResponseEntity<String> editPlatoons(@RequestBody PlatoonsDTO platoonsDTO) {
            try {
                if(!platoonsRepository.existsById(platoonsDTO.getId_platoon())) {
                    return ResponseEntity.badRequest().body("Взвода не существует");
                }

                Platoons platoon = new Platoons(platoonsDTO.getId_platoon(), platoonsDTO.getId_company());
                platoonsRepository.save(platoon);

                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Ошибка");
            }
        }
}

