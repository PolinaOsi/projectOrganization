package projectOrganization.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.entity.Associations;
import projectOrganization.repository.AssociationsRepository;

import java.util.List;

@Controller
@RequestMapping(path = "/associations")
public class AssociationsController {

        @Autowired
        private AssociationsRepository associationsRepository;

        private final ModelMapper modelMapper = new ModelMapper();

        @GetMapping("/all")
        public ResponseEntity<List<AssociationsOutDTO>> getAllAssociations() {
            try {
                List<Associations> result = associationsRepository.findAll();
                List<AssociationsOutDTO> associationsOutDTO = modelMapper.map(result, new TypeToken<List<AssociationsOutDTO>>() {
                }.getType());
                return ResponseEntity.ok(associationsOutDTO);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<AssociationsOutDTO> getAssociations (@PathVariable Integer id) {
            try {
                if (associationsRepository.existsById(id)) {
                    Associations result = associationsRepository.findById(id).get();
                    AssociationsOutDTO associationsOutDTO = modelMapper.map(result, AssociationsOutDTO.class);
                    return ResponseEntity.ok(associationsOutDTO);
                }
                return ResponseEntity.badRequest().body(null);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteAssociations(@PathVariable Integer id) {
            try {
                if (!associationsRepository.existsById(id)) {
                    return ResponseEntity.badRequest().body("Объединения не существует");
                }
                associationsRepository.deleteById(id);
                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(" ");
            }
        }

    @PostMapping("/add")
    public ResponseEntity<String> addAssociations(@RequestBody AssociationsDTO associationsDTO ) {
        try {
            associationsDTO.setId_association(null);

            Associations association = new Associations(null, associationsDTO.getType_association(),
                    associationsDTO.getNum_association(),
                    associationsDTO.getName_association());

            associationsRepository.save(association);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<String> editAssociations(@RequestBody AssociationsDTO associationsDTO) {
        try {
            if(!associationsRepository.existsById(associationsDTO.getId_association())) {
                return ResponseEntity.badRequest().body("Объединения не существует");
            }

            Associations association = new Associations(associationsDTO.getId_association(),
                    associationsDTO.getType_association(),
                    associationsDTO.getNum_association(),
                    associationsDTO.getName_association());
            associationsRepository.save(association);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
    }

