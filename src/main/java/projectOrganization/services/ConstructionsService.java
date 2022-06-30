package projectOrganization.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import projectOrganization.dto.ConstructionsDTO;
import projectOrganization.entity.Constructions;
import projectOrganization.entity.Military_units;
import projectOrganization.repository.ConstructionsRepository;
import projectOrganization.repository.Military_unitsRepository;

import java.util.List;

@Service
public class ConstructionsService {
        @Autowired
        ConstructionsRepository constructionsRepository;
        @Autowired
        Military_unitsRepository military_unitsRepository;

        public List<Constructions> getAllConstructions() throws Exception {
            return (List<Constructions>) constructionsRepository.findAll();
        }

        public Constructions getConstructions(Integer id_construction) {
            return constructionsRepository.findById(id_construction).get();
        }

        public void addConstructions(ConstructionsDTO request) {
            Constructions construction = new Constructions();
            construction.setId_construction(request.getId_construction());
            construction.setName_construction(request.getName_construction());
            Military_units military_units = military_unitsRepository.findById(request.getId_construction()).get();
            construction.setMilitary_units(military_units);

            constructionsRepository.save(construction);
        }

        public void deleteConstructions(Integer id_construction) {
            Constructions construction = constructionsRepository.findById(id_construction).get();
            constructionsRepository.delete(construction);
        }

        public ResponseEntity<String> editConstructions(@RequestBody ConstructionsDTO constructionsDTO) {
            try {
                if(!constructionsRepository.existsById(constructionsDTO.getId_construction())) {
                    return ResponseEntity.badRequest().body("Сооружения не существует");
                }

                Constructions construction = constructionsRepository.findById(constructionsDTO.getId_construction()).get();

                construction.setId_construction(constructionsDTO.getId_construction());
                construction.setName_construction(constructionsDTO.getName_construction());
                Military_units military_units = military_unitsRepository.findById(constructionsDTO.getId_construction()).get();
                construction.setMilitary_units(military_units);
                constructionsRepository.save(construction);

                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Ошибка");
            }
        }

}
