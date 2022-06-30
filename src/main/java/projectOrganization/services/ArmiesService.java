package projectOrganization.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import projectOrganization.dto.ArmiesDTO;
import projectOrganization.entity.Armaments;
import projectOrganization.entity.Armies;
import projectOrganization.repository.ArmiesRepository;

import java.util.List;

@Service
public class ArmiesService {
    @Autowired
    private ArmiesRepository armiesRepository;

    public List<Armies> getAllArmies() throws Exception {
        return (List<Armies>) armiesRepository.findAll();
    }

    public Armies getArmies(Integer id_army) {
        return armiesRepository.findById(id_army).get();
    }

    public void addArmies(ArmiesDTO request) {
        Armies army = new Armies();

        army.setId_army(request.getId_army());
        army.setName_army(request.getName_army());
        armiesRepository.save(army);
    }

    public void deleteArmies(Integer id_army) {
        Armies army = armiesRepository.findById(id_army).get();
        armiesRepository.delete(army);
    }

    public ResponseEntity<String> editArmies(@RequestBody ArmiesDTO armiesDTO) {
        try {
            if(!armiesRepository.existsById(armiesDTO.getId_army())) {
                return ResponseEntity.badRequest().body("Армии не существует");
            }

            Armies army = armiesRepository.findById(armiesDTO.getId_army()).get();
            army.setId_army(armiesDTO.getId_army());
            army.setName_army(armiesDTO.getName_army());
            armiesRepository.save(army);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
