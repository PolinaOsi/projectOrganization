package projectOrganization.controllers;

import projectOrganization.exceptions.BadRequest;
import projectOrganization.models.Military_unitsModel;
import projectOrganization.dto.Request1DTO;
import projectOrganization.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin( origins = "*", maxAge = 3500)
@RestController
@RequestMapping("/requests")

public class RequestController {
    @Autowired
    RequestService service;

    @GetMapping("/1")
    public ResponseEntity<?> getUnitsArmamentMoreTen (@RequestBody Request1DTO request) {
        try {
            System.out.println("awd");
            List<Military_unitsModel> military_unitsModelsList = new ArrayList<>();
            service.getMilitary_unitByFilter(request).forEach(military_unit -> military_unitsModelsList.add(Military_unitsModel.toModel(military_unit)));
            System.out.println("hui");
            return ResponseEntity.ok(military_unitsModelsList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

