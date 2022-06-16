package projectOrganization.services;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Service;
        import org.springframework.web.bind.annotation.RequestBody;
        import projectOrganization.dto.ArmamentsDTO;
        import projectOrganization.entity.Armaments;
        import projectOrganization.repository.ArmamentsRepository;

        import java.util.List;

@Service
public class ArmamentsService {
    @Autowired
    ArmamentsRepository armamentsRepository;

    public List<Armaments> getAllArmaments() throws Exception {
        return (List<Armaments>) armamentsRepository.findAll();
    }

    public Armaments getArmaments(Integer id_armament) {
        return armamentsRepository.findById(id_armament).get();
    }

    public void addArmaments(ArmamentsDTO request) {
        Armaments armaments = new Armaments();

        armaments.setId_armament(request.getId_armament());
        armaments.setName_armament(request.getName_armament());
        armaments.setCount_armament(request.getCount_armament());
        armaments.setId_unit(request.getId_unit());
        armamentsRepository.save(armaments);
    }

    public void deleteArmaments(Integer id_armament) {
        Armaments armament = armamentsRepository.findById(id_armament).get();
        armamentsRepository.delete(armament);
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

