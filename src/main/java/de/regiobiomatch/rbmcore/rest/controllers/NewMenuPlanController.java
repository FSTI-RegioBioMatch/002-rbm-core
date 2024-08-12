package de.regiobiomatch.rbmcore.rest.controllers;

import de.regiobiomatch.rbmcore.rest.models.newmenuplan.NewMenuPlan;
import de.regiobiomatch.rbmcore.rest.models.newmenuplan.NewMenuPlanDTO;
import de.regiobiomatch.rbmcore.rest.services.NewMenuPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/new-menu-plans")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NewMenuPlanController {

    private final NewMenuPlanService newMenuPlanService;

    @Autowired
    public NewMenuPlanController(NewMenuPlanService newMenuPlanService) {
        this.newMenuPlanService = newMenuPlanService;
    }

    @PostMapping
    public ResponseEntity<NewMenuPlan> createMenuPlan(@RequestBody NewMenuPlanDTO menuPlanDto) {
        NewMenuPlan savedMenuPlan = newMenuPlanService.saveMenuPlan(menuPlanDto);
        return ResponseEntity.ok(savedMenuPlan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewMenuPlan> getMenuPlanById(@PathVariable String id, @RequestParam String companyId) {
        return newMenuPlanService.getMenuPlanByIdAndCompanyId(id, companyId);
    }

    @GetMapping
    public ResponseEntity<List<NewMenuPlan>> getAllMenuPlans(@RequestParam String companyId) {
        List<NewMenuPlan> menuPlans = newMenuPlanService.getAllMenuPlansByCompanyId(companyId);
        return ResponseEntity.ok(menuPlans);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenuPlan(@PathVariable String id, @RequestParam String companyId) {
        return newMenuPlanService.deleteMenuPlanByIdAndCompanyId(id, companyId);
    }

    @DeleteMapping("/{menuPlanId}/events/{eventId}")
    public ResponseEntity<?> deleteEventFromMenuPlan(
            @PathVariable String menuPlanId,
            @PathVariable String eventId,
            @RequestParam String companyId) {
        return newMenuPlanService.deleteEventFromMenuPlan(menuPlanId, eventId, companyId);
    }
}