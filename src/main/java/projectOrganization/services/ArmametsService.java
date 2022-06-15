package projectOrganization.services;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import projectOrganization.dto.ArmamentsDTO;
import projectOrganization.dto.ArmamentsOutDTO;
import projectOrganization.entity.Armaments;
import projectOrganization.repository.ArmamentsRepository;

import java.util.List;

@Service
public class ArmametsService {
    @Autowired
    ArmamentsRepository armamentsRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public ResponseEntity<List<ArmamentsOutDTO>> getAllArmaments() {
        try {
            List<Armaments> result = armamentsRepository.findAll();
            List<ArmamentsOutDTO> armamentsOutDTO = modelMapper.map(result, new TypeToken<List<ArmamentsOutDTO>>() {
            }.getType());
            return ResponseEntity.ok(armamentsOutDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<ArmamentsOutDTO> getArmaments (@PathVariable Integer id) {
        try {
            if (armamentsRepository.existsById(id)) {
                Armaments result = armamentsRepository.findById(id).get();
                ArmamentsOutDTO armamentsOutDTO = modelMapper.map(result, ArmamentsOutDTO.class);
                return ResponseEntity.ok(armamentsOutDTO);
            }
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<String> addArmaments(@RequestBody ArmamentsDTO armamentsDTO ) {
        try {
            armamentsDTO.setId_armament(null);

            Armaments armaments = new Armaments(null, armamentsDTO.getName_armament(), armamentsDTO.getCount_armament() ,armamentsDTO.getId_unit());

            armamentsRepository.save(armaments);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    public ResponseEntity<String> deleteArmaments(@PathVariable Integer id) {
        try {
            if (!armamentsRepository.existsById(id)) {
                return ResponseEntity.badRequest().body("Вооружения не существует");
            }
            armamentsRepository.deleteById(id);
            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(" ");
        }
    }

    public ResponseEntity<String> editArmaments(@RequestBody ArmamentsDTO armamentsDTO) {
        try {
            if(!armamentsRepository.existsById(armamentsDTO.getId_armament())) {
                return ResponseEntity.badRequest().body("Вооружения не существует");
            }

            Armaments armaments = new Armaments(armamentsDTO.getId_armament(), armamentsDTO.getName_armament(), armamentsDTO.getCount_armament(), armamentsDTO.getId_unit());
            armamentsRepository.save(armaments);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}