
package projectOrganization.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.entity.Technics;
import projectOrganization.repository.TechnicsRepository;

import java.util.List;

@Controller
@RequestMapping(path = "/technics")
public class TechnicsController {
        @Autowired
        private TechnicsRepository technicsRepository;

        private final ModelMapper modelMapper = new ModelMapper();

        @GetMapping("/all")
        public ResponseEntity<List<TechnicsOutDTO>> getAllTechnics() {
            try {
                List<Technics> result = technicsRepository.findAll();
                List<TechnicsOutDTO> technicsOutDTO = modelMapper.map(result, new TypeToken<List<TechnicsOutDTO>>() {
                }.getType());
                return ResponseEntity.ok(technicsOutDTO);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<TechnicsOutDTO> getTechnics(@PathVariable Integer id) {
            try {
                if (technicsRepository.existsById(id)) {
                    Technics result = technicsRepository.findById(id).get();
                    TechnicsOutDTO technicsOutDTO = modelMapper.map(result, TechnicsOutDTO.class);
                    return ResponseEntity.ok(technicsOutDTO);
                }
                return ResponseEntity.badRequest().body(null);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteTechnics(@PathVariable Integer id) {
            try {
                if (!technicsRepository.existsById(id)) {
                    return ResponseEntity.badRequest().body("Техники не существует");
                }
                technicsRepository.deleteById(id);
                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(" ");
            }
        }

        @PostMapping("/add")
        public ResponseEntity<String> addTechnics(@RequestBody TechnicsDTO technicsDTO ) {
            try {
                technicsDTO.setId_technic(null);

                Technics technics = new Technics(null, technicsDTO.getName_technic(), technicsDTO.getCount_technic(), technicsDTO.getId_unit());

                technicsRepository.save(technics);

                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Ошибка");
            }
        }

        @PostMapping("/edit")
        public ResponseEntity<String> editTechnics(@RequestBody TechnicsDTO technicsDTO) {
            try {
                if(!technicsRepository.existsById(technicsDTO.getId_technic())) {
                    return ResponseEntity.badRequest().body("Техники не существует");
                }

                Technics technics = new Technics(technicsDTO.getId_technic(), technicsDTO.getName_technic(), technicsDTO.getCount_technic(), technicsDTO.getId_unit());
                technicsRepository.save(technics);

                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Ошибка");
            }
        }
}

