package projectOrganization.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import projectOrganization.dto.ConstructionsDTO;
import projectOrganization.entity.Constructions;
import projectOrganization.repository.ConstructionsRepository;

import java.util.List;

@Service
public class ConstructionsService {
        @Autowired
        ConstructionsRepository constructionsRepository;

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
            construction.setId_unit(request.getId_unit());

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

                Constructions construction = new Constructions(constructionsDTO.getId_construction(), constructionsDTO.getName_construction(), constructionsDTO.getId_unit());
                constructionsRepository.save(construction);

                return ResponseEntity.ok("Успех");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Ошибка");
            }
        }

}
