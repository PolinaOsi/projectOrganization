package projectOrganization.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.entity.Ranks;
import projectOrganization.repository.RanksRepository;

import java.util.List;

@Controller
@RequestMapping(path = "/ranks")
public class RanksController {
        @Autowired
        private RanksRepository ranksRepository;

        private final ModelMapper modelMapper = new ModelMapper();

        @GetMapping("/all")
        public ResponseEntity<List<RanksOutDTO>> getAllRanks() {
            try {
                List<Ranks> result = ranksRepository.findAll();
                List<RanksOutDTO> ranksOutDTO = modelMapper.map(result, new TypeToken<List<RanksOutDTO>>() {
                }.getType());
                return ResponseEntity.ok(ranksOutDTO);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<RanksOutDTO> getRanks(@PathVariable Integer id) {
            try {
                if (ranksRepository.existsById(id)) {
                    Ranks result = ranksRepository.findById(id).get();
                    RanksOutDTO ranksOutDTO = modelMapper.map(result, RanksOutDTO.class);
                    return ResponseEntity.ok(ranksOutDTO);
                }
                return ResponseEntity.badRequest().body(null);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteRanks(@PathVariable Integer id) {
            try {
                if (!ranksRepository.existsById(id)) {
                    return ResponseEntity.badRequest().body("Звания не существует");
                }
                ranksRepository.deleteById(id);
                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(" ");
            }
        }

        @PostMapping("/add")
        public ResponseEntity<String> addRanks(@RequestBody RanksDTO ranksDTO ) {
            try {
                ranksDTO.setId_rank(null);

                Ranks rank = new Ranks(null, ranksDTO.getName_rank(), ranksDTO.getCategory());

                ranksRepository.save(rank);

                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Ошибка");
            }
        }

        @PostMapping("/edit")
        public ResponseEntity<String> editRanks(@RequestBody RanksDTO ranksDTO) {
            try {
                if(!ranksRepository.existsById(ranksDTO.getId_rank())) {
                    return ResponseEntity.badRequest().body("Звания не существует");
                }

                Ranks rank = new Ranks(ranksDTO.getId_rank(), ranksDTO.getName_rank(), ranksDTO.getCategory());
                ranksRepository.save(rank);

                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Ошибка");
            }
        }
}
