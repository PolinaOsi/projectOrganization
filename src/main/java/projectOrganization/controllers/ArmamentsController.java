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
@RequestMapping(path = "/armaments")
public class ArmamentsController {
        @Autowired
        private ArmamentsRepository armamentsRepository;

        private final ModelMapper modelMapper = new ModelMapper();

        @GetMapping("/all")
//        public ResponseEntity<List<ArmamentsOutDTO>> getAllArmaments() {
//            try {
//                List<Armaments> result = armamentsRepository.findAll();
//                List<ArmamentsOutDTO> armamentsOutDTO = modelMapper.map(result, new TypeToken<List<ArmamentsOutDTO>>() {
//                }.getType());
//                return ResponseEntity.ok(armamentsOutDTO);
//            } catch (Exception e) {
//                return ResponseEntity.badRequest().body(null);
//            }
//        }

        @GetMapping("/{id}")
//        public ResponseEntity<ArmamentsOutDTO> getArmaments (@PathVariable Integer id) {
//            try {
//                if (armamentsRepository.existsById(id)) {
//                        Armaments result = armamentsRepository.findById(id).get();
//                    ArmamentsOutDTO armamentsOutDTO = modelMapper.map(result, ArmamentsOutDTO.class);
//                    return ResponseEntity.ok(armamentsOutDTO);
//                }
//                return ResponseEntity.badRequest().body(null);
//            } catch (Exception e) {
//                return ResponseEntity.badRequest().body(null);
//            }
//        }

        @DeleteMapping("/{id}")
//        public ResponseEntity<String> deleteArmaments(@PathVariable Integer id) {
//            try {
//                if (!armamentsRepository.existsById(id)) {
//                    return ResponseEntity.badRequest().body("Вооружения не существует");
//                }
//                armamentsRepository.deleteById(id);
//                return ResponseEntity.ok("Успех");
//            } catch (Exception e) {
//                return ResponseEntity.badRequest().body(" ");
//            }
//        }

        @PostMapping("/add")
//        public ResponseEntity<String> addArmaments(@RequestBody ArmamentsDTO armamentsDTO ) {
//            try {
//                armamentsDTO.setId_armament(null);
//
//                Armaments armaments = new Armaments(null, armamentsDTO.getName_armament(), armamentsDTO.getCount_armament() ,armamentsDTO.getId_unit());
//
//                armamentsRepository.save(armaments);
//
//                return ResponseEntity.ok("Успех");
//            } catch (Exception e) {
//                return ResponseEntity.badRequest().body("Ошибка");
//            }
//        }

        @PostMapping("/edit")
//        public ResponseEntity<String> editArmaments(@RequestBody ArmamentsDTO armamentsDTO) {
//            try {
//                if(!armamentsRepository.existsById(armamentsDTO.getId_armament())) {
//                    return ResponseEntity.badRequest().body("Вооружения не существует");
//                }
//
//                Armaments armaments = new Armaments(armamentsDTO.getId_armament(), armamentsDTO.getName_armament(), armamentsDTO.getCount_armament(), armamentsDTO.getId_unit());
//                armamentsRepository.save(armaments);
//
//                return ResponseEntity.ok("Успех");
//            } catch (Exception e) {
//                return ResponseEntity.badRequest().body("Ошибка");
//            }
//        }

}

