package projectOrganization.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import projectOrganization.dto.PlatoonsDTO;
import projectOrganization.entity.Platoons;
import projectOrganization.repository.PlatoonsRepository;

import java.util.List;

@Service
public class PlatoonsService {
    @Autowired
    PlatoonsRepository platoonsRepository;
    public List<Platoons> getAllPlatoons() throws Exception {
        return (List<Platoons>) platoonsRepository.findAll();
    }

    public Platoons getPlatoons(Integer id_platoon) {
        return platoonsRepository.findById(id_platoon).get();
    }

    public void addPlatoons(PlatoonsDTO request) {
        Platoons platoon = new Platoons();

        platoon.setId_platoon(request.getId_platoon());
        platoon.setId_company(request.getId_company());

        platoonsRepository.save(platoon);
    }

    public void deletePlatoons(Integer id_platoon) {
        Platoons platoon = platoonsRepository.findById(id_platoon).get();
        platoonsRepository.delete(platoon);
    }

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

