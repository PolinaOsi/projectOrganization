package projectOrganization.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectOrganization.dto.*;
import projectOrganization.entity.Ranks;
import projectOrganization.models.Military_unitsModel;
import projectOrganization.models.RanksModel;
import projectOrganization.repository.RanksRepository;
import projectOrganization.services.RanksService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/ranks")
public class RanksController {
        @Autowired
        private RanksService ranksService;


        @GetMapping("/all")
        public ResponseEntity<?>  getAllRanks() {
            try {
                List<RanksModel> ranksModelList = new ArrayList<>();
                ranksService.getAllRanks().forEach(rank -> ranksModelList.add(RanksModel.toModel(rank)));
                return ResponseEntity.ok(ranksModelList);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @GetMapping("/{id_rank}")
        public ResponseEntity<?>  getRanks(@PathVariable Integer id_rank) {
            try {
                return ResponseEntity.ok(ranksService.getRanks(id_rank));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("/{id_rank}")
        public ResponseEntity<?>  deleteRanks(@PathVariable Integer id_rank) {
            try {
                ranksService.deleteRanks(id_rank);
                return ResponseEntity.ok("Звание удалено");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PostMapping("/add")
        public ResponseEntity<?>  addRanks(@RequestBody RanksDTO request ) {
            try {
                ranksService.addRanks(request);
                return ResponseEntity.ok("Звание добавлено");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PostMapping("/edit")
        public ResponseEntity<?>  editRanks(@RequestBody RanksDTO request) {
            try {
                ranksService.editRanks(request);
                return ResponseEntity.ok("Звание изменено");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
}
