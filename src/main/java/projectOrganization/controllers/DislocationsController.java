package projectOrganization.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.entity.Dislocations;
import projectOrganization.repository.DislocationsRepository;

import java.util.List;

    @Controller
    @RequestMapping(path = "/dislocations")
    public class DislocationsController {
        @Autowired
        private DislocationsRepository dislocationsRepository;

        private final ModelMapper modelMapper = new ModelMapper();

        @GetMapping("/all")
        public ResponseEntity<List<DislocationsOutDTO>> getAllDislocations() {
            try {
                List<Dislocations> result = dislocationsRepository.findAll();
                List<DislocationsOutDTO> dislocationsOutDTO = modelMapper.map(result, new TypeToken<List<DislocationsOutDTO>>() {
                }.getType());
                return ResponseEntity.ok(dislocationsOutDTO);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<DislocationsOutDTO> getDislocations (@PathVariable Integer id) {
            try {
                if (dislocationsRepository.existsById(id)) {
                    Dislocations result = dislocationsRepository.findById(id).get();
                    DislocationsOutDTO dislocationsOutDTO = modelMapper.map(result, DislocationsOutDTO.class);
                    return ResponseEntity.ok(dislocationsOutDTO);
                }
                return ResponseEntity.badRequest().body(null);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteDislocations(@PathVariable Integer id) {
            try {
                if (!dislocationsRepository.existsById(id)) {
                    return ResponseEntity.badRequest().body("Дислокации не существует");
                }
                dislocationsRepository.deleteById(id);
                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(" ");
            }
        }

        @PostMapping("/add")
        public ResponseEntity<String> addDislocations(@RequestBody DislocationsDTO dislocationsDTO ) {
            try {
                dislocationsDTO.setId_dislocation(null);

                Dislocations dislocation = new Dislocations(null, dislocationsDTO.getCity());

                dislocationsRepository.save(dislocation);

                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Ошибка");
            }
        }

        @PostMapping("/edit")
        public ResponseEntity<String> editDislocations(@RequestBody DislocationsDTO dislocationsDTO) {
            try {
                if(!dislocationsRepository.existsById(dislocationsDTO.getId_dislocation())) {
                    return ResponseEntity.badRequest().body("Дислокации не существует");
                }

                Dislocations dislocation = new Dislocations(dislocationsDTO.getId_dislocation(), dislocationsDTO.getCity());
                dislocationsRepository.save(dislocation);

                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Ошибка");
            }
        }
    }
