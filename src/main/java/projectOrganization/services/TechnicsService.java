package projectOrganization.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import projectOrganization.dto.TechnicsDTO;
import projectOrganization.entity.Technics;
import projectOrganization.repository.TechnicsRepository;

import java.util.List;

@Service
public class TechnicsService {
    @Autowired
    TechnicsRepository technicsRepository;

    public List<Technics> getAllTechnics() throws Exception {
        return (List<Technics>) technicsRepository.findAll();
    }

    public Technics getTechnics(Integer id_technic) {
        return technicsRepository.findById(id_technic).get();
    }

    public void addTechnics(TechnicsDTO request) {
        Technics technic = new Technics();

        technic.setId_technic(request.getId_unit());
        technic.setCategory(request.getCategory());
        technic.setName_technic(request.getName_technic());
        technic.setCount_technic(request.getCount_technic());
        technic.setId_unit(request.getId_unit());
        technicsRepository.save(technic);
    }

    public void deleteTechnics(Integer id_technic) {
        Technics technic = technicsRepository.findById(id_technic).get();
        technicsRepository.delete(technic);
    }

    public ResponseEntity<String> editTechnics(@RequestBody TechnicsDTO technicsDTO) {
        try {
            if(!technicsRepository.existsById(technicsDTO.getId_unit())) {
                return ResponseEntity.badRequest().body("Техники не существует");
            }

            Technics technic = technicsRepository.findById(technicsDTO.getId_technic()).get();

            technic.setId_technic(technicsDTO.getId_technic());
            technic.setCategory(technicsDTO.getCategory());
            technic.setName_technic(technicsDTO.getName_technic());
            technic.setCount_technic(technicsDTO.getCount_technic());
            technic.setId_unit(technicsDTO.getId_unit());
            technicsRepository.save(technic);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}

