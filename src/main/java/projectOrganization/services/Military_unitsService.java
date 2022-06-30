package projectOrganization.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import projectOrganization.dto.Military_unitsDTO;
import projectOrganization.entity.Associations;
import projectOrganization.entity.Dislocations;
import projectOrganization.entity.Military_units;
import projectOrganization.repository.AssociationsRepository;
import projectOrganization.repository.DislocationsRepository;
import projectOrganization.repository.Military_unitsRepository;

import java.util.List;

@Service
public class Military_unitsService {
    @Autowired
    Military_unitsRepository military_unitsRepository;
    @Autowired
    AssociationsRepository associationsRepository;
    @Autowired
    DislocationsRepository dislocationsRepository;

    public List<Military_units> getAllMilitaryUnits() throws Exception {
        return (List<Military_units>) military_unitsRepository.findAll();
    }

    public Military_units getMilitaryUnits(Integer id_department) {
        return military_unitsRepository.findById(id_department).get();
    }

    public void addMilitaryUnits(Military_unitsDTO request) {
        Military_units military_unit = new Military_units();

        military_unit.setId_unit(request.getId_unit());
        military_unit.setName_unit(request.getName_unit());

        Associations association = associationsRepository.findById(request.getId_association()).get();
        military_unit.setAssociations(association);
        Dislocations dislocation = dislocationsRepository.findById(request.getId_dislocation()).get();
        military_unit.getDislocations().add(dislocation);
        military_unitsRepository.save(military_unit);
    }

    public void deleteMilitaryUnits(Integer id_unit) {
        Military_units military_unit = military_unitsRepository.findById(id_unit).get();
        military_unitsRepository.delete(military_unit);
    }

    public ResponseEntity<String> editMilitaryUnit(@RequestBody Military_unitsDTO military_unitsDTO) {
        try {
            if(!military_unitsRepository.existsById(military_unitsDTO.getId_unit())) {
                return ResponseEntity.badRequest().body("Военной части не существует");
            }

            Military_units military_unit = military_unitsRepository.findById(military_unitsDTO.getId_unit()).get();

            military_unit.setId_unit(military_unitsDTO.getId_unit());
            military_unit.setName_unit(military_unitsDTO.getName_unit());
            Associations association = associationsRepository.getReferenceById(military_unitsDTO.getId_association());
            military_unit.setAssociations(association);
            Dislocations dislocation = dislocationsRepository.getReferenceById(military_unitsDTO.getId_association());
            military_unit.getDislocations().add(dislocation);
            military_unitsRepository.save(military_unit);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
