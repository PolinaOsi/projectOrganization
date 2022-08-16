package projectOrganization.controllers;

import projectOrganization.dto.Request8DTO;
import projectOrganization.models.Military_unitsModel;
import projectOrganization.dto.Request12DTO;
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

    @GetMapping("/12")
    public ResponseEntity<?> getUnitsArmamentMoreTen (@RequestBody Request12DTO request) {
        try {
            List<Military_unitsModel> military_unitsModelsList = new ArrayList<>();
            service.getMilitary_unitByFilter12(request).forEach(military_unit -> military_unitsModelsList.add(Military_unitsModel.toModel(military_unit)));
            return ResponseEntity.ok(military_unitsModelsList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/8")
    public ResponseEntity<?> getUnitsTechnicMoreFive (@RequestBody Request8DTO request) {
        try {
            List<Military_unitsModel> military_unitsModelsList = new ArrayList<>();
            service.getMilitary_unitByFilter8(request).forEach(military_unit -> military_unitsModelsList.add(Military_unitsModel.toModel(military_unit)));
            return ResponseEntity.ok(military_unitsModelsList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

