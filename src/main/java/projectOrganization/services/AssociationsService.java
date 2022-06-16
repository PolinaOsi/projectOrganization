package projectOrganization.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import projectOrganization.dto.ArmamentsDTO;
import projectOrganization.dto.AssociationsDTO;
import projectOrganization.entity.Armaments;
import projectOrganization.entity.Associations;
import projectOrganization.repository.AssociationsRepository;

import java.util.List;

@Service
public class AssociationsService {
    @Autowired
    private AssociationsRepository associationsRepository;

    public List<Associations> getAllAssociations() throws Exception {
        return (List<Associations>) associationsRepository.findAll();
    }

    public Associations getAssociations(Integer id_assotiation) {
        return associationsRepository.findById(id_assotiation).get();
    }

    public void addAssociations(AssociationsDTO request) {
        Associations association = new Associations();

        association.setId_association(request.getId_association());
        association.setType_association(request.getType_association());
        association.setNum_association(request.getNum_association());
        association.setName_association(request.getName_association());
        associationsRepository.save(association);
    }

    public void deleteAssociations(Integer id_association) {
        Associations association = associationsRepository.findById(id_association).get();
        associationsRepository.delete(association);
    }

    public ResponseEntity<String> editAssociations(@RequestBody AssociationsDTO associationsDTO) {
        try {
            if(!associationsRepository.existsById(associationsDTO.getId_association())) {
                return ResponseEntity.badRequest().body("Объединения не существует");
            }

            Associations association = new Associations(associationsDTO.getId_association(), associationsDTO.getType_association(),
                    associationsDTO.getNum_association(), associationsDTO.getName_association());
            associationsRepository.save(association);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
