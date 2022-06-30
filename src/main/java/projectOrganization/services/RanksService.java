package projectOrganization.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import projectOrganization.dto.RanksDTO;
import projectOrganization.entity.Ranks;
import projectOrganization.repository.RanksRepository;

import java.util.List;

@Service
public class RanksService {
    @Autowired
    RanksRepository ranksRepository;

    public List<Ranks> getAllRanks() throws Exception {
        return (List<Ranks>) ranksRepository.findAll();
    }

    public Ranks getRanks(Integer id_rank) {
        return ranksRepository.findById(id_rank).get();
    }

    public void addRanks(RanksDTO request) {
        Ranks ranks = new Ranks();

        ranks.setId_rank(request.getId_rank());
        ranks.setName_rank(request.getName_rank());
        ranks.setCategory(request.getCategory());
        ranksRepository.save(ranks);
    }

    public void deleteRanks(Integer id_rank) {
        Ranks rank = ranksRepository.findById(id_rank).get();
        ranksRepository.delete(rank);
    }

    public ResponseEntity<String> editRanks(@RequestBody RanksDTO ranksDTO) {
        try {
            if(!ranksRepository.existsById(ranksDTO.getId_rank())) {
                return ResponseEntity.badRequest().body("Звания не существует");
            }

            Ranks rank = ranksRepository.findById(ranksDTO.getId_rank()).get();

            rank.setId_rank(ranksDTO.getId_rank());
            rank.setName_rank(ranksDTO.getName_rank());
            rank.setCategory(ranksDTO.getCategory());
            ranksRepository.save(rank);

            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}

