package projectOrganization.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import projectOrganization.dto.DislocationsDTO;
import projectOrganization.entity.Dislocations;
import projectOrganization.repository.DislocationsRepository;

import java.util.List;

@Service
public class DislocationsService {
    @Autowired
    DislocationsRepository dislocationsRepository;

    public List<Dislocations> getAllDislocations() throws Exception {
        return (List<Dislocations>) dislocationsRepository.findAll();
    }

    public Dislocations getDislocations(Integer id_dislocation) {
        return dislocationsRepository.findById(id_dislocation).get();
    }

    public void addDislocations(DislocationsDTO request) {
        Dislocations dislocation = new Dislocations();

        dislocation.setId_dislocation(request.getId_dislocation());
        dislocation.setCity(request.getCity());
        dislocationsRepository.save(dislocation);
    }

    public void deleteDislocations(Integer id_dislocation) {
        Dislocations dislocation = dislocationsRepository.findById(id_dislocation).get();
        dislocationsRepository.delete(dislocation);
    }

    public ResponseEntity<String> editDislocations(@RequestBody DislocationsDTO dislocationsDTO) {
        try {
            if(!dislocationsRepository.existsById(dislocationsDTO.getId_dislocation())) {
                return ResponseEntity.badRequest().body("Дислокации не существует");
            }

            Dislocations dislocation = new Dislocations(dislocationsDTO.getId_dislocation(), dislocationsDTO.getCity());
            dislocationsRepository.save(dislocation);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

}
